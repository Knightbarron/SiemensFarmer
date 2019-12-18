package com.github.tenx.xcom.data.models.functions.appointments;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FarmerAppointmentsResponse {

    @SerializedName("farmer")
    private String farmer_id;

    @SerializedName("appointments")
    private ArrayList<AppointmentBody> mList;

    public String getFarmer_id() {
        return farmer_id;
    }

    public void setFarmer_id(String farmer_id) {
        this.farmer_id = farmer_id;
    }

    public ArrayList<AppointmentBody> getmList() {
        return mList;
    }

    public void setmList(ArrayList<AppointmentBody> mList) {
        this.mList = mList;
    }

    public FarmerAppointmentsResponse(String farmer_id, ArrayList<AppointmentBody> mList) {
        this.farmer_id = farmer_id;
        this.mList = mList;
    }

    public class AppointmentBody{

        @SerializedName("confirmStatus")
        private boolean confirmStatus;

        @SerializedName("_id")
        private String id;

        @SerializedName("farmer")
        private String farmerId;

        @SerializedName("expert")
        private String expertId;

        @SerializedName("price")
        private String price;

        public boolean getConfirmStatus() {
            return confirmStatus;
        }

        public void setConfirmStatus(boolean confirmStatus) {
            this.confirmStatus = confirmStatus;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFarmerId() {
            return farmerId;
        }

        public void setFarmerId(String farmerId) {
            this.farmerId = farmerId;
        }

        public String getExpertId() {
            return expertId;
        }

        public void setExpertId(String expertId) {
            this.expertId = expertId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public AppointmentBody(boolean confirmStatus, String id, String farmerId, String expertId, String price) {
            this.confirmStatus = confirmStatus;
            this.id = id;
            this.farmerId = farmerId;
            this.expertId = expertId;
            this.price = price;
        }
    }

}
