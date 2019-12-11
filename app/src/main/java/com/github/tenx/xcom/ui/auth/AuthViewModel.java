package com.github.tenx.xcom.ui.auth;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;

public class AuthViewModel extends BaseViewModel implements AuthViewModelHelper {

    private AppDataManager appDataManager;

    private static final String TAG = "AuthViewModel";


    public AuthViewModel(AppDataManager dataManager) {
        super(dataManager);
        this.appDataManager = dataManager;
    }


    public String getString(){
        return "i am a manuh . Do you understand Goru 36??";
    }
}
