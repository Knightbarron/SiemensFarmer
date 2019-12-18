package com.github.tenx.xcom.ui.Services;

import androidx.lifecycle.LiveData;

import com.github.tenx.xcom.data.models.DefaultResponse;
import com.github.tenx.xcom.data.models.functions.equipments.AllEquipmentsResponse;

public interface ServicesViewModelHelper {

    LiveData<AllEquipmentsResponse> getAllEquipments();

    LiveData<Boolean> getStatusForOrder();

}
