package com.github.tenx.xcom.ui.Services.requestDistributionOfCrop;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.services.distribution.DistributionBody;
import com.github.tenx.xcom.ui.Services.ServicesViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestDistributionFragment extends Fragment {

    @BindView(R.id.crop_name)
    TextInputEditText cropName;
    @BindView(R.id.line1)
    TextInputLayout line1;
    @BindView(R.id.tv_description)
    TextInputEditText tvDescription;
    @BindView(R.id.line2)
    TextInputLayout line2;
    @BindView(R.id.crop_quantity)
    TextInputEditText cropQuantity;
    @BindView(R.id.line3)
    TextInputLayout line3;
    @BindView(R.id.tv_location)
    TextInputEditText tvLocation;
    @BindView(R.id.line4)
    TextInputLayout line4;
    @BindView(R.id.spin_kit)
    ProgressBar spinKit;
    @BindView(R.id.singin)
    MaterialButton singin;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @BindView(R.id.layout)
    LinearLayout layout;

    private static final String TAG = "RequestDistributionFrag";

    @Inject
    ServicesViewModel viewModel;


    @Inject
    public RequestDistributionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_distribution, container, false);


        AndroidSupportInjection.inject(this);

        ButterKnife.bind(this,view);


        subscribeObserver();

        return view;
    }

    private void subscribeObserver() {
        viewModel.getStatusCreateDIstRequest().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    spinKit.setVisibility(View.GONE);

                    Snackbar.make(layout,"Successfully requested",Snackbar.LENGTH_SHORT).show();
                }else{
                    Snackbar.make(layout,"Error occured",Snackbar.LENGTH_SHORT).show();
                    spinKit.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @OnClick(R.id.singin)
    public void onViewClicked() {

        String strName = cropName.getText().toString();
        String strDescription = tvDescription.getText().toString();
        int strQuantity = Integer.parseInt(cropQuantity.getText().toString());
        String strLocation = tvLocation.getText().toString();

        if (TextUtils.isEmpty(strName) ) {
            Snackbar.make(layout, "Enter Crop name", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(strDescription) ) {
            Snackbar.make(layout, "Enter Description", Snackbar.LENGTH_LONG).show();
            return;
        }


        if (TextUtils.isEmpty(strLocation) ) {
            Snackbar.make(layout, "Enter Location", Snackbar.LENGTH_LONG).show();
            return;
        }

        //TODO the network call.
        viewModel.postCreateDistRequest(new DistributionBody(strName,strDescription,strQuantity,strLocation));

    }
}
