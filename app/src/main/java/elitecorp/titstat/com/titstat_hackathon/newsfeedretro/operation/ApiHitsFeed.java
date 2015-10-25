package elitecorp.titstat.com.titstat_hackathon.newsfeedretro.operation;


import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo.ApiHIts;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo.MembersList;
import elitecorp.titstat.com.titstat_hackathon.newsfeedretro.retofit.RestCallback;
import retrofit.http.GET;

/**
 * Created by prodapt on 06/08/15.
 */
public interface ApiHitsFeed {
    @GET("/tipstat?type=json&query=api_hits")
    public void getApiHitsFeed(RestCallback<ApiHIts> callback);
}
