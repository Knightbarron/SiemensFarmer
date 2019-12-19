package com.github.tenx.xcom.data.models.functions.appointments;

import com.google.gson.annotations.SerializedName;

public class AppointmentsREsponse {
    @SerializedName("message")
    private String message;

    @SerializedName("details")
    private Data mData;

    public class Data{

        @SerializedName("confirmStatus")
        boolean confirm;

        public Data(boolean confirm) {
            this.confirm = confirm;
        }

        public boolean isConfirm() {
            return confirm;
        }

        public void setConfirm(boolean confirm) {
            this.confirm = confirm;
        }
    }
}
