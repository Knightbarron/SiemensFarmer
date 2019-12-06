package com.github.tenx.xcom.ui.main.home;

import androidx.lifecycle.ViewModel;

import com.github.tenx.xcom.data.AppDataManager;

public class HomeViewModel  extends ViewModel {

    private AppDataManager appDataManager;

    public HomeViewModel(AppDataManager appDataManager) {
        this.appDataManager = appDataManager;
    }
}