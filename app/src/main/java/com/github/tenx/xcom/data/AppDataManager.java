package com.github.tenx.xcom.data;

import android.content.Context;

import com.github.tenx.xcom.data.models.DefaultResponse;
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
import com.github.tenx.xcom.data.models.services.distribution.CropPriceResponse;
import com.github.tenx.xcom.data.models.services.distribution.DistributionBody;
import com.github.tenx.xcom.data.models.services.distribution.DistributionPriceResponse;
import com.github.tenx.xcom.data.models.services.distribution.PredictionBody;
import com.github.tenx.xcom.data.prefs.AppPreferencesHelper;
import com.github.tenx.xcom.data.rest.events.AppEventHelper;
import com.github.tenx.xcom.di.scopes.ApplicationContext;
import com.google.gson.JsonElement;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

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
    public Observable<Response<RegistrationResponse>> registerFarmer(RegistrationBody body) {
        return eventHelper.registerFarmer(body);
    }

    @Override
    public Observable<Response<DefaultResponse>> loginFarmer(LoginBody body) {
        return eventHelper.loginFarmer(body);
    }

    @Override
    public Observable<Response<FarmerAppointmentsResponse>> getAppointmentsForFarmer() {
        return eventHelper.getAppointmentsForFarmer();
    }

    @Override
    public Observable<Response<ResponseForAppointmentCreation>> postCreateAppointment(String id, FarmerAppointmentsBody body) {
        return eventHelper.postCreateAppointment(id,body);
    }

    @Override
    public Observable<Response<AllExpertsResponse>> getAllExperts() {
        return eventHelper.getAllExperts();
    }

    @Override
    public Observable<Response<ExpertProfileBody>> getSingleExpert(String id) {
        return eventHelper.getSingleExpert(id);
    }

    @Override
    public Observable<Response<DefaultResponse>> createEquipment(EquipmentBody body) {
        return eventHelper.createEquipment(body);
    }

    @Override
    public Observable<Response<AllEquipmentsResponse>> getAllEquipments() {
        return eventHelper.getAllEquipments();
    }

    @Override
    public Observable<Response<DefaultResponse>> orderEquipment(OrderEquipmentBody body) {
        return eventHelper.orderEquipment(body);
    }

    @Override
    public Observable<Response<AllEquipmentsResponse>> getEquipmentsForFarmer() {
        return eventHelper.getEquipmentsForFarmer();
    }

    @Override
    public Observable<Response<GetAllProductsResponse>> getAllProducts() {
        return eventHelper.getAllProducts();
    }

    @Override
    public Observable<Response<MyProfileBody>> patchMyProfile( MyProfileBody body) {
        return eventHelper.patchMyProfile(body);
    }

    @Override
    public Observable<Response<CropPriceResponse>> getMyCropPrice() {
        return eventHelper.getMyCropPrice();
    }

    @Override
    public Observable<Response<DefaultResponse>> createDistributionRequest(DistributionBody body) {
        return eventHelper.createDistributionRequest(body);
    }

    @Override
    public Observable<Response<DistributionPriceResponse>> getPredictedPrice(PredictionBody body) {
        return eventHelper.getPredictedPrice(body);
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
    public void setUserId(String userId) {
        preferencesHelper.setUserId(userId);
    }

    @Override
    public String getUserId() {
        return preferencesHelper.getUserId();
    }
}
