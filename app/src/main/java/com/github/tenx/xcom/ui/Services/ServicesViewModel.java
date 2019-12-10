package com.github.tenx.xcom.ui.Services;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;

import javax.inject.Inject;

public class ServicesViewModel extends BaseViewModel implements ServicesViewModelHelper {


    private AppDataManager appDataManager;

    private static final String TAG = "ServicesViewModel";


    public ServicesViewModel(AppDataManager dataManager) {

        super(dataManager);
        this.appDataManager = dataManager;
    }
}
