package com.github.tenx.xcom.data.rest.events;

import com.github.tenx.xcom.data.models.EventResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

public class AppEventHelper implements  EventsApiService {
    private static AppEventHelper instance;
    private EventsApiService api;


    @Inject
    public AppEventHelper(Retrofit retrofit) {
        api = retrofit.create(EventsApiService.class);
    }

    @Override
    public Call<ArrayList<EventResponse>> getEvents() {
        return api.getEvents();
    }

}
