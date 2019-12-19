package com.github.tenx.xcom.data.models.services.distribution;

import com.google.gson.annotations.SerializedName;

public class DistributionBody {

    @SerializedName("cropName")
    private String cropName;
    @SerializedName("description")
    private String description;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("location")
    private String location;

    public DistributionBody(String cropName, String description, int  quantity, String location) {
        this.cropName = cropName;
        this.description = description;
        this.quantity = quantity;
        this.location = location;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int  quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
