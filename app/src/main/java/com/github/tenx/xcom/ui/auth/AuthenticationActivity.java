package com.github.tenx.xcom.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.ui.auth.login.LoginFragment;
import com.github.tenx.xcom.ui.auth.registration.RegistrationFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public class AuthenticationActivity extends AppCompatActivity implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    AuthViewModel viewModel;

    private static final String TAG = "debuggable";

    FragmentManager fragmentManager;

    @Inject
    LoginFragment loginFragment;

    @Inject
    RegistrationFragment registrationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        AndroidInjection.inject(this);

        if (viewModel==null)
            Log.d(TAG, "onCreate: ViewmOdel is null");
        else
            Log.d(TAG, "onCreate: ViewModel is not null");


        Log.d(TAG, "onCreate: " + viewModel.getString());


        fragmentManager = getSupportFragmentManager();
        initFrag(loginFragment);

        viewModel.setAuthToken("Alpha Beta Gamma delta");
        Log.d(TAG, "onCreate: Answer is::: " + viewModel.getAuthToken() );


    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


    private void initFrag(Fragment fragment) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();

    }
}
