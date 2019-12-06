package com.github.tenx.xcom.ui.main.home;



import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.EventResponse;
import com.github.tenx.xcom.ui.main.MainViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

public class HomeFragment extends Fragment {




//    google fragment lifecycle or https://developer.android.com/guide/components/fragments if you are unsure about how to use fragment lifecycle
    @Inject
    MainViewModel mainViewModel;

    @BindView(R.id.frag_home_tv_demo)
    TextView tvDemo;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View parent =  inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, parent);


        return parent;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel.loadEvents();

    }

    @Override
    public void onStart() {
        super.onStart();


//        this data is fetched from the main view model. so it is available till the mainactivity is NOT Destroyed
//        if you implement this from the HomeViewModel it will refresh after switching tabs in bot nav
        mainViewModel.getEvents().observe(this, res -> {
           String temp = "";
            for(EventResponse e : res){
               temp += e.getName() + "\n";
               temp += e.getDate() + "\n";
               temp += e.getImageUrl() + "\n";
               temp += "-----\n";
           }
            tvDemo.setText(temp);
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

    }
}
