package com.github.tenx.xcom.data.rest.events;

import com.github.tenx.xcom.data.models.UserData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface EventsApiService {


    @GET("users/Knightbarron/repos")
    Observable<List<UserData>> getEvents();

}
