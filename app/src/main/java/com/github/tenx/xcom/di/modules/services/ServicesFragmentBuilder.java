package com.github.tenx.xcom.di.modules.services;

import com.github.tenx.xcom.ui.Services.buyEquipment.BuyEquipmentFragment;
import com.github.tenx.xcom.ui.Services.equipments.EquipmentsFragment;
import com.github.tenx.xcom.ui.Services.payments.FarmerPaymentsFragment;
import com.github.tenx.xcom.ui.Services.distribution.DistributionFragment;
import com.github.tenx.xcom.ui.Services.requestDistributionOfCrop.RequestDistributionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServicesFragmentBuilder {

    @ContributesAndroidInjector(modules = {})
    abstract EquipmentsFragment bindEquipmentsFragment();

    @ContributesAndroidInjector(modules = {})
    abstract DistributionFragment bindStorageFragment();

    @ContributesAndroidInjector
    abstract BuyEquipmentFragment bindBuyEquipmentsFragment();

    @ContributesAndroidInjector
    abstract FarmerPaymentsFragment bindFarmerPaymentsFragment();

    @ContributesAndroidInjector
    abstract RequestDistributionFragment bindRequestDistributionFragment();


}
