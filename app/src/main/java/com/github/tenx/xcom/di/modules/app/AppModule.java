package com.github.tenx.xcom.di.modules.app;


import android.app.Application;
import android.content.Context;

import com.github.tenx.xcom.di.scopes.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    static Context provideContext(Application application){return application;}

}
