package com.github.tenx.xcom.data.models.services.distribution;

import com.google.gson.annotations.SerializedName;

public class DistributionPriceResponse {

    @SerializedName("data")
    private Data data;


    public class Data{

        private String rate;
        private String total;
        private String distCost;
        private String farmerRevenue;

        public Data(String rate, String total, String distCost, String farmerRevenue) {
            this.rate = rate;
            this.total = total;
            this.distCost = distCost;
            this.farmerRevenue = farmerRevenue;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getDistCost() {
            return distCost;
        }

        public void setDistCost(String distCost) {
            this.distCost = distCost;
        }

        public String getFarmerRevenue() {
            return farmerRevenue;
        }

        public void setFarmerRevenue(String farmerRevenue) {
            this.farmerRevenue = farmerRevenue;
        }
    }

}
