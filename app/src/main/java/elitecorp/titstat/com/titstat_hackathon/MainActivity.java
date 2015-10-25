package elitecorp.titstat.com.titstat_hackathon;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import elitecorp.titstat.com.titstat_hackathon.Handler.DatabaseHandler;
import elitecorp.titstat.com.titstat_hackathon.Util.TextAwesome;
import elitecorp.titstat.com.titstat_hackathon.adapter.MemberAdapter;
import elitecorp.titstat.com.titstat_hackathon.adapter.RecyclerItemClickListener;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo.ApiHIts;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo.MembersList;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo.MembersListDetails;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.operation.ApiHitsFeed;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.operation.MembersListFeed;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.retofit.RestCallback;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.retofit.RestError;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.retofit.RetofitRestAdapter;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    TextAwesome totalMemberCount,totalApiCount,tvSortHeight,tvSortWeight,tvFilter;
    ProgressBar pb_member;
    MemberAdapter adapter;
    DatabaseHandler db;
    LinearLayout ll;
    public int TOTAL_LIST_ITEMS;
    public int NUM_ITEMS_PAGE   = 10;
    private int noOfBtns;
    private Button[] btns;
    ArrayList<MembersListDetails> members;
    private boolean pages;

    ArrayList<MembersListDetails> sort;
    MembersListDetails membersListDetails;
    private String[] arrayValues = {"ALL","Asian", "Indian", "African Americans", "Asian Americans", "European", "British", "Jewish", "Latino", "Native American", "Arabic"};
    private String selectedItem = "ALL";
    private String[] items;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.app_name) + "</font>")));
        db = new DatabaseHandler(getApplicationContext());
        membersListDetails = new MembersListDetails();
         /* Initialize recyclerview */
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        pb_member = (ProgressBar) findViewById(R.id.pb_member);
        totalApiCount = (TextAwesome) findViewById(R.id.tv_ApiHitCount);
        totalMemberCount = (TextAwesome) findViewById(R.id.tv_MemberCount);
        tvSortHeight = (TextAwesome) findViewById(R.id.tv_sort_height);
        tvSortWeight = (TextAwesome) findViewById(R.id.tv_sort_weight);
        tvFilter = (TextAwesome) findViewById(R.id.tv_filter);

        ll = (LinearLayout)findViewById(R.id.btnLay);
        tvSortHeight.setText(getResources().getString(R.string.fa_sort_alpha_asc)+" Height");
        tvSortWeight.setText(getResources().getString(R.string.fa_sort_alpha_asc)+" Weight");
        tvFilter.setText(getResources().getString(R.string.fa_filter) + " Ethnicity");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = membersListDetails.getEthnicityValue();

        tvSortHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sortMethod("height");

            }
        });


     /*   tvSortWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortMethod("weight");

            }
        });*/


        MembersListFeed membersListFeed = RetofitRestAdapter.getRestAdapter().create(MembersListFeed.class);
        membersListFeed.getNewsListFeed(new RestCallback<MembersList>() {
            @Override
            public void success(MembersList newsResponse, Response response) {

                pb_member.setVisibility(View.GONE);
                members = newsResponse.getMembers();
                totalMemberCount.setText(getResources().getString(R.string.fa_bar_chart) + "  Total Members : " + members.size());

                value = db.getAllContacts().size();
                if (value == 0) {
                    for (MembersListDetails membersListDetails : members) {
                        int weight = Integer.valueOf(membersListDetails.getWeight()) / 1000;

                        db.addContact(new MembersListDetails(membersListDetails.getId(), membersListDetails.getStatus(), items[Integer.valueOf(membersListDetails.getEthnicity())], membersListDetails.getDob(), membersListDetails.getHeight(), String.valueOf(weight), membersListDetails.getIs_veg(), membersListDetails.getDrink(), "NO", membersListDetails.getImage()));
                    }
                }

                Btnfooter(members);

                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {
                    reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String result = sb.toString();
                Log.v("Success", result);

                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // do whatever
                                List<MembersListDetails> members = db.getAllContacts();

                                for (MembersListDetails member : members) {
                                    Log.e("est", member.getWeight());
                                }
                                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                intent.putExtra("Array", sort.get(position));
                                startActivity(intent);
                            }
                        })
                );
            }

            @Override
            public void failure(RestError error) {
                // something went wrong
                Log.e("There are some problem", error.toJSON());
            }
        });

        ApiHitsFeed apiUserRegistration = RetofitRestAdapter.getRestAdapter().create(ApiHitsFeed.class);
        apiUserRegistration.getApiHitsFeed(new RestCallback<ApiHIts>() {
            @Override
            public void success(ApiHIts newsResponse, Response response) {

                String items = newsResponse.getApi_hits();
                totalApiCount.setText(getResources().getString(R.string.fa_code_fork) + " API Hits : " + items);
                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {
                    reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String result = sb.toString();
                Log.v("Success", result);
            }

            @Override
            public void failure(RestError error) {
                // something went wrong
                Log.e("There are some problem", error.toJSON());
            }
        });



        tvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    private void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Make your selection");

        builder.setItems(arrayValues, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                tvFilter.setText(getResources().getString(R.string.fa_filter) + " " + arrayValues[item]);
               selectedItem = arrayValues[item];
                sortMethod("height");

              // sort = db.getFilterEtinic(items[item]);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

  private void sortMethod(String heightOrWeight)
    {
        pages = false;

        int size = db.getFilterEtinic(selectedItem,heightOrWeight).size();


        Btnfooter(db.getFilterEtinic(selectedItem, heightOrWeight));

        if(size == 0)
            Toast.makeText(getApplicationContext(),"No Results Found",Toast.LENGTH_SHORT).show();

    }
    private void Btnfooter(final ArrayList<MembersListDetails> members)
    {

        TOTAL_LIST_ITEMS = members.size();


        int val = TOTAL_LIST_ITEMS%NUM_ITEMS_PAGE;
        val = val==0?0:1;
        noOfBtns=TOTAL_LIST_ITEMS/NUM_ITEMS_PAGE+val;


        ll.removeAllViews();

        btns    =new Button[noOfBtns];

        for(int i=0;i<noOfBtns;i++)
        {
            btns[i] =   new Button(this);
            btns[i].setBackgroundColor(getResources().getColor(R.color.primary_dark));
            btns[i].setText(""+(i+1));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            ll.addView(btns[i], lp);

            final int j = i;


            btns[j].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                   loadList(j,members);
                   CheckBtnBackGroud(j);
                }
            });


        }

        if(pages == false) {
            loadList(0,members);
            CheckBtnBackGroud(0);
            pages = true;
        }

    }

    /**
     * Method for Checking Button Backgrounds
     */
    private void CheckBtnBackGroud(int index)
    {

        for(int i=0;i<noOfBtns;i++)
        {
            if(i==index)
            {
                btns[index].setBackgroundColor(getResources().getColor(R.color.primary_dark));
                btns[i].setTextColor(getResources().getColor(android.R.color.white));
            }
            else
            {
                btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(android.R.color.black));
            }
        }

    }

    /**
     * Method for loading data in listview
     * @param number
     */
    private void loadList(int number,ArrayList<MembersListDetails> members)
    {
        sort  = new ArrayList<MembersListDetails>();

        int start = number * NUM_ITEMS_PAGE;
        for(int i=start;i<(start)+NUM_ITEMS_PAGE;i++)
        {
            if(i<members.size())
            {
                sort.add(members.get(i));
            }
            else
            {
                break;
            }
        }


        adapter = new MemberAdapter(getApplicationContext(),sort,value);

        mRecyclerView.setAdapter(adapter);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,FavouritesActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
