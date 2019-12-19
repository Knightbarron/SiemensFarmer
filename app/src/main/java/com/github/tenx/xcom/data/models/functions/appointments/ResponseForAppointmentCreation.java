package com.github.tenx.xcom.data.models.functions.appointments;

import com.google.gson.annotations.SerializedName;

public class ResponseForAppointmentCreation {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getDetails() {
        return Details;
    }

    public void setDetails(Data details) {
        Details = details;
    }

    @SerializedName("details")
    private Data Details;

    public ResponseForAppointmentCreation(String message, Data details) {
        this.message = message;
        Details = details;
    }

    public class Data{

        private String description;

        @SerializedName("_id")
        private String id;

        public Data(String description, String id) {
            this.description = description;
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


}
