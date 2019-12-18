package com.github.tenx.xcom.di.modules.app;



import com.github.tenx.xcom.config.Config;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public abstract class AppRetrofitModule {

    private static Retrofit.Builder builder = new Retrofit.Builder().
            baseUrl(Config.REST_BASE_URL).
            addConverterFactory(GsonConverterFactory.create());

//    private static Retrofit retrofit = builder.
//            addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
//            build();




    @Provides
    @Singleton
    static Retrofit provideRetrofit(){
        builder = new Retrofit.Builder().baseUrl(Config.REST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build();



    }


}
