package com.github.tenx.xcom.data;

import android.content.Context;

import com.github.tenx.xcom.data.models.UserData;
import com.github.tenx.xcom.data.prefs.AppPreferencesHelper;
import com.github.tenx.xcom.data.rest.events.AppEventHelper;
import com.github.tenx.xcom.di.scopes.ApplicationContext;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AppDataManager implements  AppDataManagerHelper{


    private Context context;
    private AppEventHelper eventHelper;
    private AppPreferencesHelper preferencesHelper;


    @Inject
    public AppDataManager(@ApplicationContext Context context, AppEventHelper appEventHelper,
                          AppPreferencesHelper preferencesHelper) {
        this.context = context;
        this.eventHelper = appEventHelper;
        this.preferencesHelper = preferencesHelper;
    }


    @Override
    public Observable<List<UserData>> getEvents() {
        return eventHelper.getEvents();
    }


    @Override
    public String getAccessToken() {
        return preferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String token) {
        preferencesHelper.setAccessToken(token);
    }

    @Override
    public void setEmail(String email) {
        preferencesHelper.setEmail(email);
    }

    @Override
    public String getEmail() {
        return preferencesHelper.getEmail();
    }

    @Override
    public String getTypeUser() {
        return preferencesHelper.getTypeUser();
    }

    @Override
    public void setTypeUser(String userType) {
        preferencesHelper.setTypeUser(userType);
    }
}
