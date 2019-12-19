package com.github.tenx.xcom.ui.Function;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.tenx.xcom.data.models.DefaultResponse;
import com.github.tenx.xcom.data.models.functions.appointments.AllExpertsResponse;
import com.github.tenx.xcom.data.models.functions.appointments.ExpertProfileBody;
import com.github.tenx.xcom.data.models.functions.appointments.FarmerAppointmentsResponse;
import com.github.tenx.xcom.data.models.functions.equipments.AllEquipmentsResponse;
import com.github.tenx.xcom.data.models.functions.equipments.EquipmentBody;
import com.github.tenx.xcom.data.models.functions.profile.MyProfileBody;
import com.github.tenx.xcom.data.models.products.GetAllProductsResponse;

public interface FunctionViewModelHelper {

    LiveData<FarmerAppointmentsResponse> getAppointmentsForFarmer();

    LiveData<Boolean> getResponsePostAppointment();

    LiveData<AllExpertsResponse> getAllExperts();

    LiveData<ExpertProfileBody> getSingleExpert();

    LiveData<Boolean> statusCreateEquipment();

    LiveData<AllEquipmentsResponse> getEquipmentsForFarmer();

    LiveData<GetAllProductsResponse> getAllProducts();

    LiveData<Boolean> patchMyProfile( );
}
