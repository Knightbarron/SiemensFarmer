package com.github.tenx.xcom.data;

import android.content.Context;

import com.github.tenx.xcom.data.models.UserData;
import com.github.tenx.xcom.data.rest.events.AppEventHelper;
import com.github.tenx.xcom.di.scopes.ApplicationContext;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AppDataManager implements  AppDataManagerHelper{


    private Context context;
    private AppEventHelper eventHelper;


    @Inject
    public AppDataManager(@ApplicationContext Context context, AppEventHelper appEventHelper) {
        this.context = context;
        this.eventHelper = appEventHelper;
    }


    @Override
    public Observable<List<UserData>> getEvents() {
        return eventHelper.getEvents();
    }


}
