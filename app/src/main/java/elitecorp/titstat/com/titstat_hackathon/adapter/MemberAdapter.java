package elitecorp.titstat.com.titstat_hackathon.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import elitecorp.titstat.com.titstat_hackathon.Handler.DatabaseHandler;
import elitecorp.titstat.com.titstat_hackathon.R;
import elitecorp.titstat.com.titstat_hackathon.RoundTransformation;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo.MembersListDetails;


/**
 * Created by Edwin on 28/02/2015.
 */
public class MemberAdapter extends SelectableAdapter<MemberAdapter.ViewHolder> {

    Context context;
    public static List<MembersListDetails> mItems;

    DatabaseHandler db;
    int dbValue;
    //  private String[] ethnicityValue = {"Asian","Indian","African Americans","Asian Americans","European","British","Jewish","Latino","Native American","Arabic"};

    public MemberAdapter(Context context, List<MembersListDetails> mItems,int dbValue) {
        super();
        this.context = context;
        this.mItems = mItems;
        this.dbValue= dbValue;
        db = new DatabaseHandler(context);
        notifyDataSetChanged();
    }


    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    public void onBindViewHolder(final ViewHolder viewHolder,int i) {
        final MembersListDetails member = mItems.get(i);
        viewHolder.tvStatus.setText(member.getStatus());
        String[] array = member.getEthnicityValue();
        try {
            viewHolder.tvEthinity.setText(array[Integer.valueOf(member.getEthnicity())]);
        }
        catch(NumberFormatException error)
        {
            viewHolder.tvEthinity.setText(member.getEthnicity());
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String[] memberYear = member.getDob().split("-");
        year = year - Integer.valueOf(memberYear[0]);
        //viewHolder.tvDob.setText("Age : "+String.valueOf(year));


        if(dbValue == 0)
//        db.addContact(new MembersListDetails(member.getId(), member.getStatus(), array[Integer.valueOf(member.getEthnicity())], member.getDob(), member.getHeight(), member.getWeight(), member.getIs_veg(), member.getDrink(), "NO",member.getImage()));

        if (member.getIs_veg().equalsIgnoreCase("1"))
            viewHolder.imgVeg.setImageResource(R.drawable.veg);

        else
            viewHolder.imgVeg.setImageResource(R.drawable.non_veg);

        if (member.getDrink().equalsIgnoreCase("1"))
            viewHolder.imgDrink.setImageResource(R.drawable.drink);

        else
            viewHolder.imgDrink.setImageResource(R.drawable.no_drink);


        //viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        Picasso.with(context)
                .load(member.getImage())
                .error(R.drawable.no_avatar)
                .transform(new RoundTransformation(10))
                .into(viewHolder.imgThumbnail);

        viewHolder.rl_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(getClass().getSimpleName(), "Status" + member.getStatus());
//                viewHolder.imgFavourite.setImageResource(R.drawable.favorie);
                viewHolder.imgFavourite.setColorFilter(R.color.primary);
            }
        });
    }


    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView tvStatus;
        public TextView tvEthinity;
        public ImageView imgFavourite;
        public ImageView imgVeg;
        public ImageView imgDrink;
        private CheckBox cb;
        public RelativeLayout rl_favourite;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
            tvEthinity = (TextView) itemView.findViewById(R.id.tv_ethinity);
            imgFavourite = (ImageView) itemView.findViewById(R.id.img_favourites_item);
            imgVeg = (ImageView) itemView.findViewById(R.id.img_veg);
            imgDrink = (ImageView) itemView.findViewById(R.id.img_drink);
            rl_favourite = (RelativeLayout) itemView.findViewById(R.id.rl_favourite);

        }
    }

}
