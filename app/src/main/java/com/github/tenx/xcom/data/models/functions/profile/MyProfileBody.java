package com.github.tenx.xcom.data.models.functions.profile;

import com.google.gson.annotations.SerializedName;

public class MyProfileBody {

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastNAme;

    @SerializedName("location")
    private String location;

    @SerializedName("bio")
    private String bio ;

    public MyProfileBody(String firstName, String lastNAme, String location, String bio) {
        this.firstName = firstName;
        this.lastNAme = lastNAme;
        this.location = location;
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNAme() {
        return lastNAme;
    }

    public void setLastNAme(String lastNAme) {
        this.lastNAme = lastNAme;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
