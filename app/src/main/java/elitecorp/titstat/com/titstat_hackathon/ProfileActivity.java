package elitecorp.titstat.com.titstat_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import elitecorp.titstat.com.titstat_hackathon.Handler.DatabaseHandler;
import elitecorp.titstat.com.titstat_hackathon.Util.TextAwesome;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo.MembersListDetails;

public class ProfileActivity extends AppCompatActivity {


    private DatabaseHandler db;
    private ImageView imgPhoto;
    private ImageView imgFavorite;
    private EditText status;
    private TextAwesome tvEnthinity;
    private TextAwesome tvBirthday;
    private TextAwesome tvHeight;
    private TextAwesome tvWeight;
    private TextAwesome tvVeg;
    private TextAwesome tvDrink;

    // ArrayList<MembersListDetails> members;
    MembersListDetails members;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.title_activity_profile_activity) + "</font>")));
        db = new DatabaseHandler(getApplicationContext());

        imgPhoto = (ImageView) findViewById(R.id.img_thumbnail);
        imgFavorite = (ImageView) findViewById(R.id.img_fav);
        status = (EditText) findViewById(R.id.statuse_msg);
        tvEnthinity = (TextAwesome) findViewById(R.id.tv_ethinity);
        tvBirthday = (TextAwesome) findViewById(R.id.tv_dob);
        tvHeight = (TextAwesome) findViewById(R.id.tv_height);
        tvWeight = (TextAwesome) findViewById(R.id.tv_weight);
        tvVeg = (TextAwesome) findViewById(R.id.tv_veg);
        tvDrink = (TextAwesome) findViewById(R.id.tv_drink);

        Intent get = getIntent();
        members = (MembersListDetails) get.getSerializableExtra("Array");
        String[] array = members.getEthnicityValue();
        status.setText(members.getStatus());
        try {
            tvEnthinity.setText(array[Integer.valueOf(members.getEthnicity())]);
        }
        catch(NumberFormatException error) {
            tvEnthinity.setText(members.getEthnicity());
        }


        tvBirthday.setText(this.getResources().getString(R.string.fa_birthday_cake) + "  Birthday : " + members.getDob());
        tvHeight.setText(this.getResources().getString(R.string.fa_text_height)+"  Height : "+ members.getHeight() + " CM");
        tvWeight.setText(this.getResources().getString(R.string.fa_database)+"  Weight : "+members.getWeight() + " KG");
        if(members.getIs_veg().equalsIgnoreCase("1"))
        tvVeg.setText(this.getResources().getString(R.string.fa_tree)+"  Veg : Yes");

        else
            tvVeg.setText(this.getResources().getString(R.string.fa_tree)+"  Veg : No");

        if(members.getDrink().equalsIgnoreCase("1"))
            tvDrink.setText(this.getResources().getString(R.string.fa_glass)+"  Drink : Yes");

        else
            tvDrink.setText(this.getResources().getString(R.string.fa_glass)+"  Drink : No");



        //viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        Picasso.with(getApplicationContext())
                .load(members.getImage())
                .error(R.drawable.no_avatar)
                .transform(new RoundTransformation(10))
                .into(imgPhoto);

        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("FAV",members.getId());
                imgFavorite.setBackgroundResource(R.drawable.favorie);
                db.updateContact(new MembersListDetails(members.getId(),"YES"));


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_p_rofilea_ctivity, menu);
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
