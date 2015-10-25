package elitecorp.titstat.com.titstat_hackathon.newsfeedretro.jdo;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by LOKESH on 10/17/2015.
 */
public class MembersListDetails implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("dob")
    private String dob;
    @SerializedName("status")
    private String status;
    @SerializedName("ethnicity")
    private String ethnicity;
    @SerializedName("weight")
    private String weight;
    @SerializedName("height")
    private String height;

    @SerializedName("is_veg")
    private String is_veg;
    @SerializedName("drink")
    private String drink;
    @SerializedName("image")
    private String image;

    private String favorite;

    public MembersListDetails() {

    }

    public String[] getEthnicityValue() {
        return ethnicityValue;
    }

    public void setEthnicityValue(String[] ethnicityValue) {
        this.ethnicityValue = ethnicityValue;
    }

    private String[] ethnicityValue = {"Asian", "Indian", "African Americans", "Asian Americans", "European", "British", "Jewish", "Latino", "Native American", "Arabic"};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getIs_veg() {
        return is_veg;
    }

    public void setIs_veg(String is_veg) {
        this.is_veg = is_veg;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public MembersListDetails(String id, String status, String ethnicity, String dob, String height, String weight, String is_veg, String drink, String favorite,String image) {
        this.id = id;

        this.status = status;
        this.ethnicity = ethnicity;
        this.dob = dob;
        this.height = height;
        this.weight = weight;

        this.is_veg = is_veg;
        this.drink = drink;

        this.favorite = favorite;
        this.image = image;

    }

    public MembersListDetails(String id, String favorite) {
        this.id = id;
        this.favorite = favorite;
    }

}
