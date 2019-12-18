package com.github.tenx.xcom.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.data.models.auth.LoginBody;
import com.github.tenx.xcom.data.models.auth.RegistrationBody;
import com.github.tenx.xcom.data.models.auth.RegistrationResponse;
import com.github.tenx.xcom.data.prefs.AppPreferencesHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AuthViewModel extends BaseViewModel implements AuthViewModelHelper {

    private AppDataManager appDataManager;

    private static final String TAG = "AuthViewModel";

    //LiveData declarations
    MutableLiveData<Boolean> registrationResponse;
    MutableLiveData<Boolean> loginResponse;



    //LiveData Getters

    public LiveData<Boolean> getRegistrationResponse(){
        if (registrationResponse==null)
            registrationResponse = new MutableLiveData<>();
        return registrationResponse;
    }

    public LiveData<Boolean> getLoginResponse(){
        if (loginResponse==null)
            loginResponse = new MutableLiveData<>();
        return loginResponse;
    }








    public AuthViewModel(AppDataManager dataManager) {
        super(dataManager);
        this.appDataManager = dataManager;

    }


    //TEST
    public String getString(){
        return "i am a manuh . Do you understand Goru 36??";
    }


    public void setAuthToken(String token){

        Log.d(TAG, "getAuthToken: " + token);
        appDataManager.setAccessToken(token);
    }

    public String getAuthToken(){

        return appDataManager.getAccessToken();
    }

    public void setUserEmail(String email){
        appDataManager.setEmail(email);
    }

    public String getUserEmail(){
        return appDataManager.getEmail();
    }

    public void setUserId(String id){
        appDataManager.setUserId(id);
    }

    public String getUserId(){
        return appDataManager.getUserId();
    }


    //TODO check this
    public void registerFarmer(RegistrationBody body){
        if (registrationResponse==null)
            registrationResponse = new MutableLiveData<>();

        appDataManager.registerFarmer(body).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(new Observer<Response<RegistrationResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<RegistrationResponse> registrationResponseResponse) {

                if (registrationResponseResponse.code()==201){
                    setAuthToken(registrationResponseResponse.body().getToken());
                    setUserEmail(registrationResponseResponse.body().getmData().getEmail());
                    setUserId(registrationResponseResponse.body().getmData().getId());

                    registrationResponse.setValue(true);

                }else{
                    Log.d(TAG, "onNext: " + registrationResponseResponse.code());
                    Log.d(TAG, "onNext: Error occured.. ");
                    registrationResponse.setValue(false);
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
                registrationResponse.setValue(false);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Registration Complete." );
            }
        });


    }

    public void loginFarmer(LoginBody loginBody){


        if (loginResponse==null)
            loginResponse = new MutableLiveData<>();

        appDataManager.loginFarmer(loginBody).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<Response<RegistrationResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Response<RegistrationResponse> registrationResponseResponse) {
                            if (registrationResponseResponse.code()==200){

                                setAuthToken(registrationResponseResponse.body().getToken());
                                setUserEmail(registrationResponseResponse.body().getmData().getEmail());
                                setUserId(registrationResponseResponse.body().getmData().getId());

                                loginResponse.setValue(true);


                            }else{
                                loginResponse.setValue(false);
                                Log.d(TAG, "onNext: code::: " + registrationResponseResponse.code());
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginResponse.setValue(false);
                        Log.e(TAG, "onError: ",e );
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: LoginCompleted");
                    }
                });
    }



}
