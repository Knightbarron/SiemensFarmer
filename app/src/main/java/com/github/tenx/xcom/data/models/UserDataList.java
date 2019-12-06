package com.github.tenx.xcom.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserDataList {

    @SerializedName("")
    private ArrayList<UserData> arrayList;

    public ArrayList<UserData> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<UserData> arrayList) {
        this.arrayList = arrayList;
    }
}
