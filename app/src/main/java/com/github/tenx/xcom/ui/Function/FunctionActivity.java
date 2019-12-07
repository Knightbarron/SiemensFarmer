package com.github.tenx.xcom.ui.Function;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.ui.Function.articles.ArticlesFragment;
import com.github.tenx.xcom.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import maes.tech.intentanim.CustomIntent;

public class FunctionActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    FunctionViewModel viewModel;

    private static final String TAG = "FunctionActivity";

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    ArticlesFragment articlesFragment;

    int flagId = 0;
    @BindView(R.id.titleText)
    TextView titleText;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);

        //To make the injections
        AndroidInjection.inject(this);

        if (viewModel == null)
            Log.d(TAG, "onCreate: ViewModel is empty");
        else
            Log.d(TAG, "onCreate: ViewModel is not empty");


        flagId = getIntent().getExtras().getInt(Constants.SELECTED_ID);

        fragmentManager = getSupportFragmentManager();

        inititalizeFragment(flagId);
        Log.d(TAG, "onCreate: Flag id ::: " + flagId);

    }

    private void inititalizeFragment(int flagId) {

        switch (flagId) {


            case 5:
                initFrag(articlesFragment);
                setUpToolBar("Articles");

        }

    }

    private void setUpToolBar(String title) {
        titleText.setText(title);

    }

    private void initFrag(Fragment fragment) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;

    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this,"right-to-left");
    }
}

