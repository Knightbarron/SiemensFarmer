package com.github.tenx.xcom.ui.main;

import androidx.lifecycle.LiveData;

import com.github.tenx.xcom.data.models.EventResponse;

import java.util.ArrayList;

public interface MainViewModelHelper {
    LiveData<ArrayList<EventResponse>> getEvents();

    void loadEvents();
}
