package elitecorp.titstat.com.titstat_hackathon.newsfeedretro.retofit;

import android.util.Log;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit.RequestInterceptor;

/**
 * Created by prodapt on 06/08/15.
 */
public class SessionRequestInterceptor implements RequestInterceptor {

    public static String Username = "GRBA";
    public static String  UserOrganization= "GreatBasinPark";
    public static String  Client_Secret= "MobileApps";

    @Override
    public void intercept(RequestFacade request)
    {

        request.addHeader("Content-Type", "application/json");
        request.addHeader("authSign", getAuthSignature(generateUTC()));
        request.addHeader("requestTime", generateUTC());
        request.addHeader("userName", "GRBA");
//        request.addHeader("operatingSystem", "Android");
    }

    public static String getAuthSignature(
            String requestTime)   {


        String authSig = null;
        MessageDigest digest = null;
        try {
            digest   = MessageDigest.getInstance("SHA-1");


            byte[] bytes = (Username
                    + UserOrganization
                    + Client_Secret + requestTime)
                    .getBytes("UTF-8");
//            Log.i("Login as employee","compute bytes"+bytes);
            digest.update(bytes, 0, bytes.length);
            bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02X", b));
            }
            authSig = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("CustomHTTPClient", "UTC time and Authsign" + requestTime + "-" + authSig.toLowerCase());
        return authSig.toLowerCase();
    }
    public static String generateUTC(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
        Timestamp date1 =  new Timestamp(date.getTime());
        return formatter.format(date1);
    }
}