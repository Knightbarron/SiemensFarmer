package com.github.tenx.xcom.data.models.services.distribution;

import java.util.HashMap;
import java.util.Map;

public class CropPriceResponse {

    Map<String,String> hashMap;

    public Map<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    public CropPriceResponse(Map<String, String> hashMap) {
        this.hashMap = hashMap;
    }
}
