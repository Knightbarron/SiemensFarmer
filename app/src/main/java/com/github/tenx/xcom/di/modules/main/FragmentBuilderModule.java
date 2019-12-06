package com.github.tenx.xcom.di.modules.main;


import com.github.tenx.xcom.di.modules.home.HomeFragmentModule;
import com.github.tenx.xcom.di.scopes.FragmentScope;
import com.github.tenx.xcom.ui.main.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract  class FragmentBuilderModule {


    @ContributesAndroidInjector(modules = {HomeFragmentModule.class})
    @FragmentScope
    abstract HomeFragment bindHomeFragment();


}
