package com.github.tenx.xcom.data.models.functions.equipments;

import com.google.gson.annotations.SerializedName;

public class EquipmentBody {
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("description")
    private String description;
    @SerializedName("nStock")
    private int stock;
    @SerializedName("availble")
    private boolean isAvailble;
    @SerializedName("_id")
    private String id;
    @SerializedName("farmer")
    private String creatorFarmer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatorFarmer() {
        return creatorFarmer;
    }

    public void setCreatorFarmer(String creatorFarmer) {
        this.creatorFarmer = creatorFarmer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isAvailble() {
        return isAvailble;
    }

    public void setAvailble(boolean availble) {
        isAvailble = availble;
    }

    public EquipmentBody(String name, String price, String description, int stock, boolean isAvailble) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.isAvailble = isAvailble;
    }
}
