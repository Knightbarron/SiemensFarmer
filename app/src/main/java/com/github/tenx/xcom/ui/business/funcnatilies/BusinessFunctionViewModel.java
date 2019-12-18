package com.github.tenx.xcom.ui.business.funcnatilies;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;

public class BusinessFunctionViewModel extends BaseViewModel implements BusinessFunctionViewModelHelper {

    private AppDataManager appDataManager;

    public BusinessFunctionViewModel(AppDataManager appDataManager) {
        super(appDataManager);
        this.appDataManager = appDataManager;
    }

    public String getString(){
        return "LWARWAASD";
    }




}
