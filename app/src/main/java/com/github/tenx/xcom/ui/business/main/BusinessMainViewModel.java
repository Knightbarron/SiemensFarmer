package com.github.tenx.xcom.ui.business.main;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;

public class BusinessMainViewModel extends BaseViewModel implements BusinessMainViewModelHelper {


    private AppDataManager appDataManager;

    public BusinessMainViewModel(AppDataManager appDataManager) {
        super(appDataManager);
        this.appDataManager = appDataManager;
    }

    public String getString(){
        return "i am a manuh . Do you understand me??";
    }


}
