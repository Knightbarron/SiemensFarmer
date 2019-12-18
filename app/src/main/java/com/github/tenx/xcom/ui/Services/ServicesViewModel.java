package com.github.tenx.xcom.ui.Services;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.data.models.DefaultResponse;
import com.github.tenx.xcom.data.models.functions.equipments.AllEquipmentsResponse;
import com.github.tenx.xcom.data.models.functions.equipments.OrderEquipmentBody;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ServicesViewModel extends BaseViewModel implements ServicesViewModelHelper {


    private AppDataManager appDataManager;

    private static final String TAG = "ServicesViewModel";

    //Mutable LiveData Responses
    MutableLiveData<AllEquipmentsResponse> allEquipmentsResponse;

    //Mutable LiveData Status
    MutableLiveData<Boolean> allEquipmentsStatus;
    MutableLiveData<Boolean> statusOrderEquipment;

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
        return "i am a manuh . Do you understand Goru??";
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




}
