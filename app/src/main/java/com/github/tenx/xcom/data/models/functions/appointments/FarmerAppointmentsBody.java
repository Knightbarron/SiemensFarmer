package com.github.tenx.xcom.data.models.functions.appointments;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FarmerAppointmentsBody {

    @SerializedName("location")
    private String location;
    @SerializedName("price")
    private int price;
    @SerializedName("description")
    private String description;

    public FarmerAppointmentsBody(String location, int price, String description) {
        this.location = location;
        this.price = price;
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }


}
