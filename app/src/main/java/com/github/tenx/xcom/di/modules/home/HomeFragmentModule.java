package com.github.tenx.xcom.di.modules.home;


import androidx.lifecycle.ViewModelProviders;

import com.github.tenx.xcom.base.ViewModelFactory;
import com.github.tenx.xcom.data.AppDataManager;
import com.github.tenx.xcom.ui.main.MainActivity;
import com.github.tenx.xcom.ui.main.home.HomeFragment;
import com.github.tenx.xcom.ui.main.home.HomeViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public abstract  class HomeFragmentModule {

    @Provides
    static HomeViewModel provideHomeViewModel(HomeFragment fragment, AppDataManager appDataManager){
        ViewModelFactory<HomeViewModel> factory = new ViewModelFactory<>(new HomeViewModel(appDataManager));
        return ViewModelProviders.of(fragment , factory).get(HomeViewModel.class);
    }
}
