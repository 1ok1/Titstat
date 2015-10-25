package elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by prodapt on 06/08/15.
 */

public class MembersList {





    @SerializedName("members")
    private ArrayList<MembersListDetails> members;

    public ArrayList<MembersListDetails> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<MembersListDetails> members) {
        this.members = members;
    }


}
