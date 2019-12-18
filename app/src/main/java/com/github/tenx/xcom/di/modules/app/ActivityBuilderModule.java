package com.github.tenx.xcom.di.modules.app;


import com.github.tenx.xcom.di.modules.auth.AuthActivityModule;
import com.github.tenx.xcom.di.modules.auth.AuthFragmentBuilder;
import com.github.tenx.xcom.di.modules.business.businessFunctionality.BusinessFunctionalityActivityModule;
import com.github.tenx.xcom.di.modules.business.businessFunctionality.BusinessFunctionalityFragmentModule;
import com.github.tenx.xcom.di.modules.business.businessMain.BusinessMainActivityModule;
import com.github.tenx.xcom.di.modules.function.FunctionActivityModule;
import com.github.tenx.xcom.di.modules.function.FunctionFragmentBuilder;
import com.github.tenx.xcom.di.modules.main.MainActModule;
import com.github.tenx.xcom.di.modules.services.ServicesActivityModule;
import com.github.tenx.xcom.di.modules.services.ServicesFragmentBuilder;
import com.github.tenx.xcom.ui.Function.FunctionActivity;
import com.github.tenx.xcom.ui.Services.ServicesActivity;
import com.github.tenx.xcom.ui.auth.AuthenticationActivity;
import com.github.tenx.xcom.ui.business.funcnatilies.BusinessFunctionalityActivity;
import com.github.tenx.xcom.ui.business.main.BusinessMainActivity;
import com.github.tenx.xcom.ui.main.MainActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {


    @ContributesAndroidInjector(modules = {MainActModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {FunctionActivityModule.class,
            FunctionFragmentBuilder.class})
    abstract FunctionActivity bindFunctionActivity();

    @ContributesAndroidInjector(modules = {ServicesActivityModule.class,
            ServicesFragmentBuilder.class})
    abstract ServicesActivity bindServicesActivity();

    @ContributesAndroidInjector(modules = {AuthActivityModule.class,
            AuthFragmentBuilder.class})
    abstract AuthenticationActivity bindAuthenticationActivity();


    //FOr the services app

    @ContributesAndroidInjector(modules = {BusinessFunctionalityActivityModule.class,
            BusinessFunctionalityFragmentModule.class})
    abstract BusinessFunctionalityActivity bindBusinessFunctionalityActivity();

    @ContributesAndroidInjector(modules = {BusinessMainActivityModule.class})
    abstract BusinessMainActivity bindBusinessMainActivity();

}
