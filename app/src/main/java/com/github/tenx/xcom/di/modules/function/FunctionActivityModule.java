package com.github.tenx.xcom.di.modules.function;

import android.content.Context;

import androidx.lifecycle.ViewModelProviders;

import com.github.tenx.xcom.base.ViewModelFactory;
import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.di.scopes.ActivityContext;
import com.github.tenx.xcom.di.scopes.PerActivity;
import com.github.tenx.xcom.ui.Function.FunctionActivity;
import com.github.tenx.xcom.ui.Function.FunctionViewModel;
import com.github.tenx.xcom.ui.main.MainActivity;
import com.github.tenx.xcom.ui.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public abstract  class FunctionActivityModule {

    @Provides
    @PerActivity
    @ActivityContext
    static Context provideFunctionActivityModule(FunctionActivity activity){
        return activity;
    }


    @Provides
    static FunctionViewModel provideFunctionViewModel(FunctionActivity functionActivity , AppDataManager appDataManager){
        ViewModelFactory<FunctionViewModel> factory = new ViewModelFactory<>(new FunctionViewModel(appDataManager));
        return ViewModelProviders.of(functionActivity, factory).get(FunctionViewModel.class);
    }
}
