package com.github.tenx.xcom.ui.Services.distribution;

public class CropPriceModel {
    private String crop;

    private int price;

    public CropPriceModel(String crop, int   price) {
        this.crop = crop;
        this.price = price;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
