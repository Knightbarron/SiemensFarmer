package com.github.tenx.xcom.ui.Services.predictedPrice;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.services.distribution.DistributionBody;
import com.github.tenx.xcom.data.models.services.distribution.PredictionBody;
import com.github.tenx.xcom.ui.Services.ServicesViewModel;
import com.github.tenx.xcom.utils.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class PricePredictionFragment extends Fragment {

    @BindView(R.id.rate)
    TextView rate;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.discount)
    TextView discount;
    @BindView(R.id.farmer_revenue)
    TextView farmerRevenue;
    @BindView(R.id.btn_payment)
    MaterialButton btnPayment;

    @Inject
    ServicesViewModel viewModel;


    String strDescription = "";
    String strLocation = "";
    int strQuantity = 0;
    String strName = "";
    @BindView(R.id.layout)
    LinearLayout layout;

    @Inject
    public PricePredictionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_price_prediction, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);


        strDescription = getArguments().getString(Constants.DESCRIPTION);
        strLocation = getArguments().getString(Constants.LOCATION);
        strName = getArguments().getString(Constants.NAME);
        strQuantity = getArguments().getInt(Constants.QUANTITY);

        viewModel.getPredictedPrice(new PredictionBody(strName,strQuantity));




        subscribeObserver();


        return view;
    }

    private void subscribeObserver() {

        viewModel.getStatusCreateDIstRequest().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Snackbar.make(layout,"Success",Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(layout,"Failed",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @OnClick(R.id.btn_payment)
    public void onViewClicked() {

        viewModel.postCreateDistRequest(new DistributionBody(strName,strDescription,strQuantity,strLocation));

    }
}
