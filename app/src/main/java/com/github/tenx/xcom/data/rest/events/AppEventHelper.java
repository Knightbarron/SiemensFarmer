package com.github.tenx.xcom.data.rest.events;

import com.github.tenx.xcom.data.models.DefaultResponse;
import com.github.tenx.xcom.data.models.UserData;
import com.github.tenx.xcom.data.models.auth.LoginBody;
import com.github.tenx.xcom.data.models.auth.RegistrationBody;
import com.github.tenx.xcom.data.models.auth.RegistrationResponse;
import com.github.tenx.xcom.data.models.functions.appointments.AllExpertsResponse;
import com.github.tenx.xcom.data.models.functions.appointments.ExpertProfileBody;
import com.github.tenx.xcom.data.models.functions.appointments.FarmerAppointmentsBody;
import com.github.tenx.xcom.data.models.functions.appointments.FarmerAppointmentsResponse;
import com.github.tenx.xcom.data.models.functions.appointments.ResponseForAppointmentCreation;
import com.github.tenx.xcom.data.models.functions.equipments.AllEquipmentsResponse;
import com.github.tenx.xcom.data.models.functions.equipments.EquipmentBody;
import com.github.tenx.xcom.data.models.functions.equipments.OrderEquipmentBody;
import com.github.tenx.xcom.data.models.functions.profile.MyProfileBody;
import com.github.tenx.xcom.data.models.products.GetAllProductsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AppEventHelper implements  EventsApiService {
    private static AppEventHelper instance;
    private EventsApiService api;


    @Inject
    public AppEventHelper(Retrofit retrofit) {
        api = retrofit.create(EventsApiService.class);
    }



    @Override
    public Observable<Response<RegistrationResponse>> registerFarmer(RegistrationBody body) {
        return api.registerFarmer(body);
    }

    @Override
    public Observable<Response<DefaultResponse>> loginFarmer(LoginBody body) {
        return api.loginFarmer(body);
    }

    @Override
    public Observable<Response<FarmerAppointmentsResponse>> getAppointmentsForFarmer() {
        return api.getAppointmentsForFarmer();
    }

    @Override
    public Observable<Response<ResponseForAppointmentCreation>> postCreateAppointment(String id, FarmerAppointmentsBody body) {
        return api.postCreateAppointment(id,body);
    }

    @Override
    public Observable<Response<AllExpertsResponse>> getAllExperts() {
        return api.getAllExperts();
    }

    @Override
    public Observable<Response<ExpertProfileBody>> getSingleExpert(String id) {
        return api.getSingleExpert(id);
    }

    @Override
    public Observable<Response<DefaultResponse>> createEquipment(EquipmentBody body) {
        return api.createEquipment(body);
    }

    @Override
    public Observable<Response<AllEquipmentsResponse>> getAllEquipments() {
        return api.getAllEquipments();
    }

    @Override
    public Observable<Response<DefaultResponse>> orderEquipment(OrderEquipmentBody body) {
        return api.orderEquipment(body);
    }

    @Override
    public Observable<Response<AllEquipmentsResponse>> getEquipmentsForFarmer() {
        return api.getEquipmentsForFarmer();
    }

    @Override
    public Observable<Response<GetAllProductsResponse>> getAllProducts() {
        return api.getAllProducts();
    }

    @Override
    public Observable<Response<MyProfileBody>> patchMyProfile( MyProfileBody body) {
        return api.patchMyProfile( body);
    }


}
