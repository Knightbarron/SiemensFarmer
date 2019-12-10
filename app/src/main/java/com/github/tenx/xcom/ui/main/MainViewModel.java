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

    @Override
    public LiveData<List<UserData>> getEvents() {
        if(eventsList == null){
            eventsList = new MutableLiveData<>();
        }
        return  eventsList;
    }

    @Override
    public void loadEvents() {
        if(eventsList == null){
            eventsList = new MutableLiveData<>();
        }

        appDataManager.getEvents().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<UserData> userData) {
                        Log.d(TAG, "onNext: " + userData.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
