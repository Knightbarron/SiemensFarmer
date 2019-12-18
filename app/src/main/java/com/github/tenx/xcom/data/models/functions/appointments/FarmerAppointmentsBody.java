package com.github.tenx.xcom.data.models.functions.appointments;

import java.text.SimpleDateFormat;

public class FarmerAppointmentsBody {
    private String location;
    private String date;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FarmerAppointmentsBody(String location, String date) {
        this.location = location;
        this.date = date;
    }
}
