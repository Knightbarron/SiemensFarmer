package com.github.tenx.xcom.di.modules.app;


import com.github.tenx.xcom.di.modules.function.FunctionActivityModule;
import com.github.tenx.xcom.di.modules.function.FunctionFragmentBuilder;
import com.github.tenx.xcom.di.modules.main.MainActModule;
import com.github.tenx.xcom.ui.Function.FunctionActivity;
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

}
