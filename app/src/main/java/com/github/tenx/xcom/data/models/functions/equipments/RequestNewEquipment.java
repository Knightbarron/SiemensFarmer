package com.github.tenx.xcom.data.models.functions.equipments;

import com.google.gson.annotations.SerializedName;

public class RequestNewEquipment {

    @SerializedName("nQuantity")
    private int quantity;

    @SerializedName("nDays")
    private int days;

    @SerializedName("dateRequired")
    private String dateRequired;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDateRequired() {
        return dateRequired;
    }

    public void setDateRequired(String dateRequired) {
        this.dateRequired = dateRequired;
    }

    public RequestNewEquipment(int quantity, int days, String dateRequired) {
        this.quantity = quantity;
        this.days = days;
        this.dateRequired = dateRequired;
    }
}
