package com.github.tenx.xcom.data;

import android.content.Context;

import com.github.tenx.xcom.data.models.EventResponse;
import com.github.tenx.xcom.data.rest.events.AppEventHelper;
import com.github.tenx.xcom.di.scopes.ApplicationContext;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;

public class AppDataManager implements  AppDataManagerHelper{


    private Context context;
    private AppEventHelper eventHelper;


    @Inject
    public AppDataManager(@ApplicationContext Context context, AppEventHelper appEventHelper) {
        this.context = context;
        this.eventHelper = appEventHelper;
    }

    @Override
    public Call<ArrayList<EventResponse>> getEvents() {
        return eventHelper.getEvents();
    }
}
