package com.github.tenx.xcom.data.models.functions.equipments;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllEquipmentsResponse {

    @SerializedName("equipments")
    private ArrayList<EquipmentBody> mList;

    public ArrayList<EquipmentBody> getmList() {
        return mList;
    }

    public void setmList(ArrayList<EquipmentBody> mList) {
        this.mList = mList;
    }

    public AllEquipmentsResponse(ArrayList<EquipmentBody> mList) {
        this.mList = mList;
    }
}
