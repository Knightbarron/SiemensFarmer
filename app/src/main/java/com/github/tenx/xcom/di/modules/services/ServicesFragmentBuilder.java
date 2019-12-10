package com.github.tenx.xcom.di.modules.services;

import com.github.tenx.xcom.ui.Services.equipments.EquipmentsFragment;
import com.github.tenx.xcom.ui.Services.storage.StorageFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServicesFragmentBuilder {

    @ContributesAndroidInjector(modules = {})
    abstract EquipmentsFragment bindEquipmentsFragment();

    @ContributesAndroidInjector(modules = {})
    abstract StorageFragment bindStorageFragment();


}
