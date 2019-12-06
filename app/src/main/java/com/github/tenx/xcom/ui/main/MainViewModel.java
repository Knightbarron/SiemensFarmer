package com.github.tenx.xcom.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.data.models.EventResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainViewModel  extends ViewModel implements MainViewModelHelper {
    private AppDataManager appDataManager;


    private MutableLiveData<ArrayList<EventResponse>> eventsList;


    public MainViewModel(AppDataManager appDataManager) {
        this.appDataManager = appDataManager;
    }

    @Override
    public LiveData<ArrayList<EventResponse>> getEvents() {
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

        appDataManager.getEvents().enqueue(new Callback<ArrayList<EventResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<EventResponse>> call, Response<ArrayList<EventResponse>> response) {
                if(response.code() < 300){
                    eventsList.setValue(response.body());
                }else{
                    Timber.e("Response code : %d , Error fetching events" , response.code());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<EventResponse>> call, Throwable t) {
                Timber.e("Error fetching events , %s" ,t.getMessage() );
            }
        });
    }


}
