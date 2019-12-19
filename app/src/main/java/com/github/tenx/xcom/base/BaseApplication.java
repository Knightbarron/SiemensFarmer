package com.github.tenx.xcom.base;

import android.app.Activity;
import android.app.Application;

import com.github.tenx.xcom.BuildConfig;

import com.github.tenx.xcom.di.components.DaggerAppComponent;
import com.github.tenx.xcom.logging.ReleaseTree;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

public class BaseApplication extends Application implements HasActivityInjector {


    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(throwable -> {});


        DaggerAppComponent.builder().application(this).build().inject(this);



        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
        }
    }
}
