package com.github.tenx.xcom.data.models.products;

import java.util.ArrayList;

public class GetAllProductsResponse {


    private ArrayList<ProductsBody> mList;

    public ArrayList<ProductsBody> getmList() {
        return mList;
    }

    public void setmList(ArrayList<ProductsBody> mList) {
        this.mList = mList;
    }

    public GetAllProductsResponse(ArrayList<ProductsBody> mList) {
        this.mList = mList;
    }
}
