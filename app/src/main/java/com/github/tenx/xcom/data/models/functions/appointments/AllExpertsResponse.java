package com.github.tenx.xcom.data.models.functions.appointments;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllExpertsResponse {

    @SerializedName("experts")
    private ArrayList<ExpertProfileBody> mList;

    public ArrayList<ExpertProfileBody> getmList() {
        return mList;
    }

    public void setmList(ArrayList<ExpertProfileBody> mList) {
        this.mList = mList;
    }

    public AllExpertsResponse(ArrayList<ExpertProfileBody> mList) {
        this.mList = mList;
    }
}
