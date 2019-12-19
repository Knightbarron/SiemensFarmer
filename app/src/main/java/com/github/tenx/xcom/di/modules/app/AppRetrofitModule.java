package com.github.tenx.xcom.di.modules.app;



import android.util.Log;

import com.github.tenx.xcom.config.Config;
import com.github.tenx.xcom.data.prefs.AppPreferencesHelper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public  class AppRetrofitModule {

//    private static Retrofit.Builder builder = new Retrofit.Builder().
//            baseUrl(Config.REST_BASE_URL).
//            addConverterFactory(GsonConverterFactory.create());
//
////    private static Retrofit retrofit = builder.
////            addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
////            build();
//
//
//
//
//    @Provides
//    @Singleton
//    static Retrofit provideRetrofit(){
//        builder = new Retrofit.Builder().baseUrl(Config.REST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create());
//        return builder.build();
//
//
//
//    }

    private static final String TAG = "AppRetrofitModule";

    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(Config.REST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

    private static OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();



    private static HttpLoggingInterceptor logging
            = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);





    @Singleton
    @Provides
    public Retrofit provideRetrofit(AppPreferencesHelper appPreferencesHelper){

        //TODO add this
        String authToken = "Bearer "+ appPreferencesHelper.getAccessToken();
     //   Log.d(TAG, "provideRetrofit: " + authToken);

      //  String authToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZGZiYWRjNmM3YjIzZDQ1MDA3MGM4ZGMiLCJlbWFpbCI6InNhaWtpYXNvdXJhdjUwQGdtYWlsLmNvbSIsInR5cGUiOiJmYXJtZXIiLCJpYXQiOjE1NzY3NzUxMTF9.89XW682TCm241LyNwfOsB8soyxGQWg9yJTbayamzS3A" ;

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization" , authToken)
                        .method(original.method(), original.body()).build();
                return chain.proceed(request);
            }
        });

        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            httpClient.connectTimeout(60, TimeUnit.SECONDS);
            httpClient.callTimeout(60, TimeUnit.SECONDS);

            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit;
    }



}
