package com.github.tenx.xcom.di.modules.business.businessMain;

import android.content.Context;

import androidx.lifecycle.ViewModelProviders;

import com.github.tenx.xcom.base.ViewModelFactory;
import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.di.scopes.ActivityContext;
import com.github.tenx.xcom.di.scopes.PerActivity;
import com.github.tenx.xcom.ui.business.main.BusinessMainActivity;
import com.github.tenx.xcom.ui.business.main.BusinessMainViewModel;
import com.github.tenx.xcom.ui.main.MainActivity;
import com.github.tenx.xcom.ui.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class BusinessMainActivityModule {

    @Provides
    @PerActivity
    @ActivityContext
    static Context provideBusinessMainActivit(BusinessMainActivity activity){
        return activity;
    }


    @Provides
    static BusinessMainViewModel provideBusinessMainViewModel(BusinessMainActivity mainActivity , AppDataManager appDataManager){
        ViewModelFactory<BusinessMainViewModel> factory = new ViewModelFactory<>(new BusinessMainViewModel(appDataManager));
        return ViewModelProviders.of(mainActivity, factory).get(BusinessMainViewModel.class);
    }

}
