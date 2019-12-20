package com.github.tenx.xcom.ui.Services;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.data.models.DefaultResponse;
import com.github.tenx.xcom.data.models.functions.equipments.AllEquipmentsResponse;
import com.github.tenx.xcom.data.models.functions.equipments.OrderEquipmentBody;
import com.github.tenx.xcom.data.models.services.distribution.CropPriceResponse;
import com.github.tenx.xcom.data.models.services.distribution.DistributionBody;
import com.github.tenx.xcom.data.models.services.distribution.DistributionPriceResponse;
import com.github.tenx.xcom.data.models.services.distribution.PredictionBody;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ServicesViewModel extends BaseViewModel implements ServicesViewModelHelper {


    private AppDataManager appDataManager;

    private static final String TAG = "ServicesViewModel";

    //Mutable LiveData Responses
    MutableLiveData<AllEquipmentsResponse> allEquipmentsResponse;

    MutableLiveData<DistributionPriceResponse> distributionPriceResponse;

    MutableLiveData<DistributionPriceResponse> predictionPriceResponse;

    MutableLiveData<Map<String,String>> hashResponseForCrops;



    //Mutable LiveData Status
    MutableLiveData<Boolean> allEquipmentsStatus;
    MutableLiveData<Boolean> statusOrderEquipment;

    MutableLiveData<Boolean> statusCreateDisRequest;
    MutableLiveData<Boolean> predictionStatus;

    public LiveData<Map<String,String>> getHashResponseForCrops(){
        if (hashResponseForCrops==null)
            hashResponseForCrops = new MutableLiveData<>();
        return hashResponseForCrops;
    }

    public LiveData<Boolean> getPredictionPriceStatusResponse(){
        if (predictionStatus==null)
            predictionStatus = new MutableLiveData<>();
        return predictionStatus;
    }


    public LiveData<DistributionPriceResponse> getDistributionPriceResponse(){
        if (distributionPriceResponse==null)
            distributionPriceResponse = new MutableLiveData<>();
        return distributionPriceResponse;
    }

    public LiveData<DistributionPriceResponse> getPredictionPriceStatus(){

        if (predictionPriceResponse==null)
            predictionPriceResponse = new MutableLiveData<>();
        return predictionPriceResponse;

    }



    public LiveData<Boolean> getStatusCreateDIstRequest(){
        if (statusCreateDisRequest==null)
            statusCreateDisRequest = new MutableLiveData<>();

        return statusCreateDisRequest;
    }

    //Mutable LiveData Status getters

    public MutableLiveData<Boolean> getStatusAllEquipments(){
        if (allEquipmentsStatus==null)
            allEquipmentsStatus = new MutableLiveData<>();
        return allEquipmentsStatus;
    }

    //Mutable LiveData response getters
    @Override
    public LiveData<AllEquipmentsResponse> getAllEquipments() {
        if (allEquipmentsResponse==null)
            allEquipmentsResponse = new MutableLiveData<>();
        return allEquipmentsResponse;
    }

    @Override
    public LiveData<Boolean> getStatusForOrder() {
        if (statusOrderEquipment==null)
            statusOrderEquipment = new MutableLiveData<>();
        return statusOrderEquipment;
    }


    public ServicesViewModel(AppDataManager dataManager) {

        super(dataManager);
        this.appDataManager = dataManager;
    }


    public String getString(){
        return "i am PlaceHolder manuh . Do you understand Goru??";
    }

    public void getAllEquipmentsList(){
        if (allEquipmentsResponse==null)
            allEquipmentsResponse = new MutableLiveData<>();
        if (allEquipmentsStatus==null)
            allEquipmentsStatus = new MutableLiveData<>();

        appDataManager.getAllEquipments().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AllEquipmentsResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                    getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<AllEquipmentsResponse> allEquipmentsResponseResponse) {
                if (allEquipmentsResponseResponse.code()==200){

                    allEquipmentsResponse.setValue(allEquipmentsResponseResponse.body());
                    allEquipmentsStatus.setValue(true);


                }else{
                    allEquipmentsStatus.setValue(false);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
                allEquipmentsStatus.setValue(false);
            }

            @Override
            public void onComplete() {

            }
        });


    }

    public void placeEquipmentOrder(OrderEquipmentBody body){

        if (statusOrderEquipment==null)
            statusOrderEquipment = new MutableLiveData<>();

        appDataManager.orderEquipment(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<DefaultResponse> defaultResponseResponse) {

                if (defaultResponseResponse.code()==200){
                    statusOrderEquipment.setValue(true);
                }else{
                    statusOrderEquipment.setValue(false);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: `", e);
                statusOrderEquipment.setValue(false);
            }

            @Override
            public void onComplete() {

            }
        });


    }

    public void getMyCropnPrice() {

        if (hashResponseForCrops==null)
            hashResponseForCrops = new MutableLiveData<>();


        appDataManager.getMyCropPrice().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<Map<String,String>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<Map<String,String>> cropPriceResponseResponse) {
                Log.d(TAG, "onNext: Price Response::: " + cropPriceResponseResponse.code());
                if (cropPriceResponseResponse.code()==200){

                    Log.d(TAG, "onNext: Reached here");

                    Log.d(TAG, "onNext: " +cropPriceResponseResponse.body());

                    hashResponseForCrops.setValue(cropPriceResponseResponse.body());

                    Map<String,String> hash = cropPriceResponseResponse.body();


                    for (String keys:hash.keySet()){
                        Log.d(TAG, "onNext: keys::: " + keys);
                    }

                }
                else{


                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void postCreateDistRequest(DistributionBody body){
        if (statusCreateDisRequest==null)
            statusCreateDisRequest = new MutableLiveData<>();

        appDataManager.createDistributionRequest(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<DefaultResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Response<DefaultResponse> defaultResponseResponse) {

                        if (defaultResponseResponse.code()==200){

                            statusCreateDisRequest.setValue(true);

                        }else{
                            statusCreateDisRequest.setValue(false);

                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: `", e);
                        statusCreateDisRequest.setValue(true);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getPredictedPrice(PredictionBody body){

        if (predictionPriceResponse==null)
            predictionPriceResponse = new MutableLiveData<>();

        appDataManager.getPredictedPrice(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DistributionPriceResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<DistributionPriceResponse> distributionPriceResponseResponse) {

                Log.d(TAG, "onNext: COde:: " + distributionPriceResponseResponse.code());

                if (distributionPriceResponseResponse.code()==200){
                    predictionPriceResponse.setValue(distributionPriceResponseResponse.body());
                }else{

                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
            }

            @Override
            public void onComplete() {

            }
        });



    }

}
