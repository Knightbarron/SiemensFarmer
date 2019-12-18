package com.github.tenx.xcom.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.data.models.UserData;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel  extends BaseViewModel implements MainViewModelHelper {
    private AppDataManager appDataManager;

    private static final String TAG = "MainViewModel";


    private MutableLiveData<List<UserData>> eventsList;


    public MainViewModel(AppDataManager appDataManager) {
        super(appDataManager);
        this.appDataManager = appDataManager;
    }


}
