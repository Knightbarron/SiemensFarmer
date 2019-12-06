package com.github.tenx.xcom.di.modules.main;


import android.content.Context;

import androidx.lifecycle.ViewModelProviders;

import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.di.scopes.ActivityContext;
import com.github.tenx.xcom.di.scopes.PerActivity;
import com.github.tenx.xcom.ui.main.MainActivity;
import com.github.tenx.xcom.ui.main.MainViewModel;
import com.github.tenx.xcom.base.ViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public abstract  class MainActModule {

    @Provides
    @PerActivity
    @ActivityContext
    static Context provideMainActivityContext(MainActivity activity){
        return activity;
    }


    @Provides
    static MainViewModel provideMainViewModel(MainActivity mainActivity , AppDataManager appDataManager){
        ViewModelFactory<MainViewModel> factory = new ViewModelFactory<>(new MainViewModel(appDataManager));
        return ViewModelProviders.of(mainActivity, factory).get(MainViewModel.class);
    }
}
