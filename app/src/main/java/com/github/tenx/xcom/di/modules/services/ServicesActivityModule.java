package com.github.tenx.xcom.di.modules.services;

import android.content.Context;

import androidx.lifecycle.ViewModelProviders;

import com.github.tenx.xcom.base.ViewModelFactory;
import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.di.scopes.ActivityContext;
import com.github.tenx.xcom.di.scopes.PerActivity;
import com.github.tenx.xcom.ui.Function.FunctionActivity;
import com.github.tenx.xcom.ui.Function.FunctionViewModel;
import com.github.tenx.xcom.ui.Services.ServicesActivity;
import com.github.tenx.xcom.ui.Services.ServicesViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ServicesActivityModule {
    @Provides
    @PerActivity
    @ActivityContext
    static Context provideServiceActivityModule(ServicesActivity activity){
        return activity;
    }


    @Provides
    static ServicesViewModel provideServiceViewModel(ServicesActivity servicesActivity , AppDataManager appDataManager){
        ViewModelFactory<ServicesViewModel> factory = new ViewModelFactory<>(new ServicesViewModel(appDataManager));
        return ViewModelProviders.of(servicesActivity, factory).get(ServicesViewModel.class);
    }
}
