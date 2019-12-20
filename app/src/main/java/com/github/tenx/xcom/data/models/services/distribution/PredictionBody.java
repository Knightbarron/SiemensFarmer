package com.github.tenx.xcom.data.models.services.distribution;

public class PredictionBody {

    private String name;

    private int quantity;

    public PredictionBody(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
