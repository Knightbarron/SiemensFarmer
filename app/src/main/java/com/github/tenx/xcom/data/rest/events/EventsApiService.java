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

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventsApiService {


    @POST("api/farmer/register")
    Observable<Response<RegistrationResponse>> registerFarmer(@Body RegistrationBody body);

    @POST("api/farmer/login")
    Observable<Response<DefaultResponse>> loginFarmer(@Body LoginBody body);

    @GET("api/appointment/farmer/me")
    Observable<Response<FarmerAppointmentsResponse>> getAppointmentsForFarmer();

    //TODO check this
    @POST("api/appointment/create&expert={expertid}")
    Observable<Response<ResponseForAppointmentCreation>> postCreateAppointment(@Path("expertid") String id, @Body FarmerAppointmentsBody body);

    @GET("api/expert")
    Observable<Response<AllExpertsResponse>> getAllExperts();

    @GET("api/expert/{id}")
    Observable<Response<ExpertProfileBody>> getSingleExpert(@Path("id") String id);

    @POST("api/equipment/create")
    Observable<Response<DefaultResponse>> createEquipment(@Body EquipmentBody body);


    @GET("api/equipment")
    Observable<Response<AllEquipmentsResponse>> getAllEquipments();

    @POST("api/request/{equipmentid}")
    Observable<Response<DefaultResponse>> orderEquipment(@Body OrderEquipmentBody body);

    @POST("api/request/farmer/me")
    Observable<Response<AllEquipmentsResponse>> getEquipmentsForFarmer();

    @GET("api/product")
    Observable<Response<GetAllProductsResponse>> getAllProducts();

    @PATCH("api/farmer/update/me")
    Observable<Response<MyProfileBody>> patchMyProfile(@Body MyProfileBody body);






}
