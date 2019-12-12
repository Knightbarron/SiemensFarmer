package com.github.tenx.xcom.di.modules.app;


import android.app.Application;
import android.content.Context;

import com.github.tenx.xcom.data.prefs.PreferencesInfo;
import com.github.tenx.xcom.di.scopes.ApplicationContext;
import com.github.tenx.xcom.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    static Context provideContext(Application application){return application;}


    //    provide file name for shared prefs
    @Provides
    @Singleton
    @PreferencesInfo
    static String providePrefFileName(){
        return Constants.PREF_FILE_NAME;
    }



}
