package com.github.tenx.xcom.data.models.functions.appointments;

import com.google.gson.annotations.SerializedName;

public class ExpertProfileBody {

    @SerializedName("profileImage")
    private String profilePic;

    private String name;
    @SerializedName("expertise")
    private String expertise;

    @SerializedName("bio")
    private String description;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @SerializedName("_id")
    private String id;

    public ExpertProfileBody(String profilePic, String name, String expertise, String description, String firstName, String lastName, String id) {
        this.profilePic = profilePic;
        this.name = name;
        this.expertise = expertise;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExpertProfileBody(String profilePic, String name, String expertise, String description, String id) {
        this.profilePic = profilePic;
        this.name = name;
        this.expertise = expertise;
        this.description = description;
        this.id = id;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
