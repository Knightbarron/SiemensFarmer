package com.github.tenx.xcom.data.rest.events;

import com.github.tenx.xcom.data.models.EventResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventsApiService {
    @GET("/api/v1/events")
    Call<ArrayList<EventResponse>> getEvents();
}
