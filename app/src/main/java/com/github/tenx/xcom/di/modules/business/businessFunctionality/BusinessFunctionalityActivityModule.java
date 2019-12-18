package com.github.tenx.xcom.di.modules.business.businessFunctionality;

import android.content.Context;

import androidx.lifecycle.ViewModelProviders;

import com.github.tenx.xcom.base.ViewModelFactory;
import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.di.scopes.ActivityContext;
import com.github.tenx.xcom.di.scopes.PerActivity;
import com.github.tenx.xcom.ui.business.funcnatilies.BusinessFunctionViewModel;
import com.github.tenx.xcom.ui.business.funcnatilies.BusinessFunctionalityActivity;
import com.github.tenx.xcom.ui.business.main.BusinessMainActivity;
import com.github.tenx.xcom.ui.business.main.BusinessMainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public  abstract class BusinessFunctionalityActivityModule {

    @Provides
    @PerActivity
    @ActivityContext
    static Context provideBusinessFuncActivit(BusinessFunctionalityActivity activity){
        return activity;
    }


    @Provides
    static BusinessFunctionViewModel provideBusinessFuncViewModel(BusinessFunctionalityActivity funcActivity , AppDataManager appDataManager){
        ViewModelFactory<BusinessFunctionViewModel> factory = new ViewModelFactory<>(new BusinessFunctionViewModel(appDataManager));
        return ViewModelProviders.of(funcActivity, factory).get(BusinessFunctionViewModel.class);
    }

}
