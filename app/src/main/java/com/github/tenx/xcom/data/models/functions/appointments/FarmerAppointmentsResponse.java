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


        @SerializedName("price")
        private String price;

        @SerializedName("expert")
        private Expert expert;

        public boolean isConfirmStatus() {
            return confirmStatus;
        }

        public Expert getExpert() {
            return expert;
        }

        public void setExpert(Expert expert) {
            this.expert = expert;
        }

        public class Expert{


            @SerializedName("firstName")
            private String firstName;

            @SerializedName("lastName")
            private String lastName;

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }
        }

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


        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public AppointmentBody(boolean confirmStatus, String id, String price) {
            this.confirmStatus = confirmStatus;
            this.id = id;

            this.price = price;
        }
    }

}
