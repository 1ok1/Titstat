package elitecorp.titstat.com.titstat_hackathon.newsfeedretro.retofit;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by prodapt on 06/08/15.
 */
public class RetofitRestAdapter {

    private static RestAdapter REST_ADAPTER;
    static {
        setupRestClient();
    }



    private static void setupRestClient() {




        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory()) // This is the important line ;)
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();

        RestAdapter  restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(" http://tipstat.0x10.info/api")
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(new SessionRequestInterceptor())
                .build();

        REST_ADAPTER = restAdapter;
    }
    public static RestAdapter getRestAdapter()
    {
        return REST_ADAPTER;
    }
}
