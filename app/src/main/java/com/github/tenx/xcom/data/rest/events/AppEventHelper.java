package com.github.tenx.xcom.data.rest.events;

import com.github.tenx.xcom.data.models.UserData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class AppEventHelper implements  EventsApiService {
    private static AppEventHelper instance;
    private EventsApiService api;


    @Inject
    public AppEventHelper(Retrofit retrofit) {
        api = retrofit.create(EventsApiService.class);
    }


    @Override
    public Observable<List<UserData>> getEvents() {
        return api.getEvents();
    }


}
