package elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LOKESH on 10/24/2015.
 */
public class ApiHIts {
    @SerializedName("api_hits")
    String api_hits;

    public String getApi_hits() {
        return api_hits;
    }

    public void setApi_hits(String api_hits) {
        this.api_hits = api_hits;
    }
}
