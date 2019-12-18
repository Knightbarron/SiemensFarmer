package com.github.tenx.xcom.di.modules.auth;


import com.github.tenx.xcom.ui.Function.articles.ArticlesFragment;
import com.github.tenx.xcom.ui.Function.shop.ShopFragment;
import com.github.tenx.xcom.ui.auth.login.LoginFragment;
import com.github.tenx.xcom.ui.auth.registration.RegistrationFragment;
import com.github.tenx.xcom.ui.auth.selection.SelectionUserTypeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AuthFragmentBuilder {


    @ContributesAndroidInjector(modules = {})
    abstract LoginFragment bindLoginFragment();

    @ContributesAndroidInjector(modules = {})
    abstract RegistrationFragment bindRegistrationFragment();

    @ContributesAndroidInjector
    abstract SelectionUserTypeFragment bindSelectionUserTypeFragment();


}
