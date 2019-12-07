package com.github.tenx.xcom.ui.Function;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.utils.Constants;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class FunctionActivity extends AppCompatActivity {

    @Inject
    FunctionViewModel viewModel;

    private static final String TAG = "FunctionActivity";

    int flagId = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        //To make the injections
        AndroidInjection.inject(this);
        
        if (viewModel==null)
            Log.d(TAG, "onCreate: ViewModel is empty");
        else
            Log.d(TAG, "onCreate: ViewModel is not empty");


       flagId =  getIntent().getExtras().getInt(Constants.SELECTED_ID);

       inititalizeFragment(flagId);
       Log.d(TAG, "onCreate: Flag id ::: " + flagId);
    
    }

    private void inititalizeFragment(int flagId) {

        switch(flagId){



        }

    }
}
