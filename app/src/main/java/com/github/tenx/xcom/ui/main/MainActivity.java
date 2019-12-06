package com.github.tenx.xcom.ui.main;

import android.os.Bundle;

import com.github.tenx.xcom.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity{



    @Inject
    MainViewModel mainViewModel;


//    frag mans
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
//        this is neccesary bind call for BindView decorators
        ButterKnife.bind(this);


        if(mainViewModel == null){
            Timber.d("View model NOT ok");
        }else{
            Timber.d("View model is locked and loaded!");
        }



        //        initialize home fragment in main activity

//        loadFragment(new HomeFragment());


//        user logger like this
        Timber.d("Welcome to my main application");
    }




//
//    private void loadFragment(Fragment frag){
//            fm = getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.act_main_fl_container, frag).commit();
//
//
//    }


}
