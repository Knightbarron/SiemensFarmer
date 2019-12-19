package com.github.tenx.xcom.ui.Function;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.data.models.DefaultResponse;
import com.github.tenx.xcom.data.models.functions.appointments.AllExpertsResponse;
import com.github.tenx.xcom.data.models.functions.appointments.ExpertProfileBody;
import com.github.tenx.xcom.data.models.functions.appointments.FarmerAppointmentsBody;
import com.github.tenx.xcom.data.models.functions.appointments.FarmerAppointmentsResponse;
import com.github.tenx.xcom.data.models.functions.appointments.ResponseForAppointmentCreation;
import com.github.tenx.xcom.data.models.functions.equipments.AllEquipmentsResponse;
import com.github.tenx.xcom.data.models.functions.equipments.EquipmentBody;
import com.github.tenx.xcom.data.models.functions.profile.MyProfileBody;
import com.github.tenx.xcom.data.models.products.GetAllProductsResponse;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class FunctionViewModel extends BaseViewModel implements FunctionViewModelHelper {
    
    private AppDataManager appDataManager;

    private static final String TAG = "FunctionViewModel";

    //LiveData declaration
    //Responses
    MutableLiveData<FarmerAppointmentsResponse> farmerAppointmentsResonse;
    MutableLiveData<AllExpertsResponse> allExpertsResponse;
    MutableLiveData<ExpertProfileBody> singleExpertProfile;
    MutableLiveData<AllEquipmentsResponse> farmerEquipmentsResponse;
    MutableLiveData<GetAllProductsResponse> getAllProductsResponse;



    //Live Data status
    MutableLiveData<Boolean> statusFarmerAppointmentsResponse;
    MutableLiveData<Boolean> statusPostAppointment;
    MutableLiveData<Boolean> statusAllExperts;
    MutableLiveData<Boolean> statusSingleExpertProfile;
    MutableLiveData<Boolean> statusCreateEquipment;
    MutableLiveData<Boolean> farmerEquipmentsStatus;
    MutableLiveData<Boolean> statusAllProducts;
    MutableLiveData<Boolean> statusPatchProfileData;


    //Mutable LiveData Status getters

    public MutableLiveData<Boolean> getStatusFarmerEquipments(){
        if (farmerEquipmentsStatus==null)
            farmerEquipmentsStatus = new MutableLiveData<>();
        return farmerEquipmentsStatus;
    }


    //Getters for status for network calls
    public LiveData<Boolean> getStatusFarmerrAppointmentsResponse(){
        if (statusFarmerAppointmentsResponse==null)
            statusFarmerAppointmentsResponse = new MutableLiveData<>();
        return statusFarmerAppointmentsResponse;
    }
    public LiveData<Boolean> getStatusAllExperts(){
        if (statusAllExperts== null)
            statusAllExperts = new MutableLiveData<>();
        return statusAllExperts;
    }

    public LiveData<Boolean> getStatusSingleExpertProfile(){
        if (statusSingleExpertProfile==null)
            statusSingleExpertProfile = new MutableLiveData<>();
        return statusSingleExpertProfile;
    }


    public LiveData<Boolean> getStatusAllProducts(){
        if (statusAllProducts==null)
            statusAllProducts = new MutableLiveData<>();
        return statusAllProducts;
    }

    //LiveData getters
    @Override
    public LiveData<FarmerAppointmentsResponse> getAppointmentsForFarmer() {
        if (farmerAppointmentsResonse==null)
            farmerAppointmentsResonse = new MutableLiveData<>();
        return farmerAppointmentsResonse;


    }

    @Override
    public LiveData<Boolean> getResponsePostAppointment() {
        if (statusPostAppointment==null)
            statusPostAppointment = new MutableLiveData<>();
        return statusPostAppointment;
    }

    @Override
    public LiveData<AllExpertsResponse> getAllExperts() {
        if (allExpertsResponse==null)
            allExpertsResponse = new MutableLiveData<>();
        return allExpertsResponse;
    }

    @Override
    public LiveData<ExpertProfileBody> getSingleExpert() {
        if (singleExpertProfile==null)
            singleExpertProfile = new MutableLiveData<>();
        return singleExpertProfile;
    }

    @Override
    public LiveData<Boolean> statusCreateEquipment() {
        if (statusCreateEquipment==null)
            statusCreateEquipment = new MutableLiveData<>();
        return statusCreateEquipment;
    }

    @Override
    public LiveData<AllEquipmentsResponse> getEquipmentsForFarmer() {
        if (farmerEquipmentsResponse==null)
            farmerEquipmentsResponse = new MutableLiveData<>();
        return farmerEquipmentsResponse;
    }

    @Override
    public LiveData<GetAllProductsResponse> getAllProducts() {
        if (getAllProductsResponse==null)
            getAllProductsResponse = new MutableLiveData<>();
        return getAllProductsResponse;
    }

    @Override
    public LiveData<Boolean> patchMyProfile() {

        if (statusPatchProfileData==null)
            statusPatchProfileData = new MutableLiveData<>();
        return statusPatchProfileData;
    }


    public FunctionViewModel(AppDataManager dataManager) {
        super(dataManager);
        this.appDataManager = dataManager;
    }


    public String getString(){
        return "i am a manuh . Do you understand me??";
    }

    //TODO check this
    public void getAllAppointments(){

        if (statusFarmerAppointmentsResponse==null)
            statusFarmerAppointmentsResponse = new MutableLiveData<>();
        if (farmerAppointmentsResonse==null)
            farmerAppointmentsResonse = new MutableLiveData<>();


        appDataManager.getAppointmentsForFarmer().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<FarmerAppointmentsResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Response<FarmerAppointmentsResponse> farmerAppointmentsResponseResponse) {

                        Log.d(TAG, "onNext: Response code::: "+ farmerAppointmentsResponseResponse.code());
                        if (farmerAppointmentsResponseResponse.code()==200){
                            statusFarmerAppointmentsResponse.setValue(true);
                            farmerAppointmentsResonse.setValue(farmerAppointmentsResponseResponse.body());
                            Log.d(TAG, "onNext: Successfully retrieved the reponses of appointments");
                        }else{
                            statusFarmerAppointmentsResponse.setValue(false);
                            Log.d(TAG, "onNext: Wrong response code" + farmerAppointmentsResponseResponse.code());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        statusFarmerAppointmentsResponse.setValue(false);
                        Log.e(TAG, "onError: ",e );
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Appointments method completed");
                    }
                });
    }
    
    public void createAppointment(String id, FarmerAppointmentsBody body){
        
        appDataManager.postCreateAppointment(id,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseForAppointmentCreation>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<ResponseForAppointmentCreation> defaultResponseResponse) {


                Log.d(TAG, "onNext: COde::: " + defaultResponseResponse.message());

                if (defaultResponseResponse.code()==200){

                    statusPostAppointment.setValue(true);
                    Log.d(TAG, "onNext: Success");
                    
                }else{
                    statusPostAppointment.setValue(false);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
                statusPostAppointment.setValue(false);
                
            }

            @Override
            public void onComplete() {

            }
        });
        
    }

    public void getAllExpertsList(){
        if (allExpertsResponse==null)
            allExpertsResponse = new MutableLiveData<>();
        if (statusAllExperts== null)
            statusAllExperts = new MutableLiveData<>();

        appDataManager.getAllExperts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AllExpertsResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<AllExpertsResponse> allExpertsResponseResponse) {
                if (allExpertsResponseResponse.code()==200){
                    statusAllExperts.setValue(true);
                    allExpertsResponse.setValue(allExpertsResponseResponse.body());
                }else{
                    statusAllExperts.setValue(false);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
                statusAllExperts.setValue(false);
            }

            @Override
            public void onComplete() {

            }
        });


    }

    public void retrieveSingleExpertProfile(String id){

        if (singleExpertProfile==null)
            singleExpertProfile = new MutableLiveData<>();
        if (statusSingleExpertProfile==null)
            statusSingleExpertProfile = new MutableLiveData<>();


        appDataManager.getSingleExpert(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ExpertProfileBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<ExpertProfileBody> expertProfileBodyResponse) {
                    if (expertProfileBodyResponse.code()==200){
                        statusSingleExpertProfile.setValue(true);
                        singleExpertProfile.setValue(expertProfileBodyResponse.body());
                    }else{
                        Log.d(TAG, "onNext: Response code:: " + expertProfileBodyResponse.code());
                        statusSingleExpertProfile.setValue(false);
                    }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
                statusSingleExpertProfile.setValue(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void createEquipment(EquipmentBody body){
        if (statusCreateEquipment==null)
            statusCreateEquipment = new MutableLiveData<>();

        appDataManager.createEquipment(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<DefaultResponse> defaultResponseResponse) {
                if (defaultResponseResponse.code()==200){
                    statusCreateEquipment.setValue(true);
                }else{
                    statusCreateEquipment.setValue(false);
                }


            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
                statusCreateEquipment.setValue(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void getAllFarmerEquipments(){
        if (farmerEquipmentsResponse==null)
            farmerEquipmentsResponse = new MutableLiveData<>();
        if (farmerEquipmentsStatus==null)
            farmerEquipmentsStatus = new MutableLiveData<>();

        appDataManager.getAllEquipments().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AllEquipmentsResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<AllEquipmentsResponse> allEquipmentsResponseResponse) {
                if (allEquipmentsResponseResponse.code()==200){

                    farmerEquipmentsResponse.setValue(allEquipmentsResponseResponse.body());
                    farmerEquipmentsStatus.setValue(true);


                }else{
                    farmerEquipmentsStatus.setValue(false);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
                farmerEquipmentsStatus.setValue(false);
            }

            @Override
            public void onComplete() {

            }
        });


    }



    public void getAllProductsForFarmer(){

        if (getAllProductsResponse==null)
            getAllProductsResponse = new MutableLiveData<>();
        if (statusAllProducts==null)
            statusAllProducts = new MutableLiveData<>();



        appDataManager.getAllProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<GetAllProductsResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<GetAllProductsResponse> getAllProductsResponseResponse) {

                if (getAllProductsResponseResponse.code()==200){

                    getAllProductsResponse.setValue(getAllProductsResponseResponse.body());
                    statusAllProducts.setValue(true);
                }else{

                    statusAllProducts.setValue(false);
                }


            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
                statusAllProducts.setValue(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void patchFarmerProfile( MyProfileBody body){
        if (statusPatchProfileData==null)
            statusPatchProfileData = new MutableLiveData<>();

        appDataManager.patchMyProfile(body).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<MyProfileBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<MyProfileBody> myProfileBodyResponse) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


}
