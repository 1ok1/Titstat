package elitecorp.titstat.com.titstat_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import elitecorp.titstat.com.titstat_hackathon.Handler.DatabaseHandler;
import elitecorp.titstat.com.titstat_hackathon.adapter.MemberAdapter;
import elitecorp.titstat.com.titstat_hackathon.adapter.RecyclerItemClickListener;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo.MembersListDetails;

public class FavouritesActivity extends AppCompatActivity {

    ArrayList<MembersListDetails> members;

    RecyclerView mRecyclerView;
    MemberAdapter adapter;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.title_activity_favourites) + "</font>")));
        db = new DatabaseHandler(getApplicationContext());
         /* Initialize recyclerview */
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_favorites);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        members = db.getAllFavorites();
        adapter = new MemberAdapter(getApplicationContext(),members,members.size());
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever



                        Intent intent = new Intent(FavouritesActivity.this, ProfileActivity.class);
                        intent.putExtra("Array", members.get(position));
                        startActivity(intent);
                    }
                })
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favourites, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
