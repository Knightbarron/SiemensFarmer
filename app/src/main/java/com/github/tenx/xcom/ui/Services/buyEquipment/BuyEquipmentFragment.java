package com.github.tenx.xcom.ui.Services.buyEquipment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.ui.Services.ServicesViewModel;
import com.github.tenx.xcom.ui.Services.payments.FarmerPaymentsFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyEquipmentFragment extends Fragment {

    @Inject
    ServicesViewModel viewModel;

    @Inject
    FarmerPaymentsFragment farmerPaymentsFragment;
    @BindView(R.id.tv_no_of_days)
    TextInputEditText tvNoOfDays;
    @BindView(R.id.tv_quantity)
    TextInputEditText tvQuantity;
    @BindView(R.id.btn_payment)
    MaterialButton btnPayment;
    @BindView(R.id.layout)
    LinearLayout layout;


    @Inject
    public BuyEquipmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy_equipment, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);

        subscribeObserver();

        return view;
    }

    private void subscribeObserver() {
        viewModel.getStatusForOrder().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {

                    Snackbar.make(layout,"Order has been placed",Snackbar.LENGTH_SHORT).show();
                } else {

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


        String days = tvNoOfDays.getText().toString();
        String quantity = tvQuantity.getText().toString();
        //TODO get the data

        //TODO the network call


        initializeFragments(farmerPaymentsFragment);
    }

    public void initializeFragments(Fragment frag) {
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);

        transaction.replace(R.id.frame_layout, frag);
        transaction.addToBackStack(backStateName);

        transaction.commit();
    }
}
