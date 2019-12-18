package com.github.tenx.xcom.data.models.functions.equipments;

import com.google.gson.annotations.SerializedName;

public class OrderEquipmentBody {

    @SerializedName("dateRequired")
    private String dateRequired;

    @SerializedName("nQuantity")
    private int quantity;

    @SerializedName("nDays")
    private int days;

    public String getDateRequired() {
        return dateRequired;
    }

    public void setDateRequired(String dateRequired) {
        this.dateRequired = dateRequired;
    }

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

    public OrderEquipmentBody(String dateRequired, int quantity, int days) {
        this.dateRequired = dateRequired;
        this.quantity = quantity;
        this.days = days;
    }
}
