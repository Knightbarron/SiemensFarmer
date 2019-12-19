package com.github.tenx.xcom.ui.main;

import androidx.lifecycle.MutableLiveData;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;

import java.util.List;

public class MainViewModel  extends BaseViewModel implements MainViewModelHelper {
    private AppDataManager appDataManager;

    private static final String TAG = "MainViewModel";



    public MainViewModel(AppDataManager appDataManager) {
        super(appDataManager);
        this.appDataManager = appDataManager;
    }


}
