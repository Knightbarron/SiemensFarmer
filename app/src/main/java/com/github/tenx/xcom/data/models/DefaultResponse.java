package com.github.tenx.xcom.data.models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("message")
    private String status;

    @SerializedName("token")
    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DefaultResponse(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DefaultResponse(String status) {
        this.status = status;
    }
}
