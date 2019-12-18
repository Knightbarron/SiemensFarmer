package com.github.tenx.xcom.ui.Function.meetingTheExpert;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.functions.appointments.FarmerAppointmentsBody;
import com.github.tenx.xcom.ui.Function.FunctionViewModel;
import com.github.tenx.xcom.utils.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpertMeetingFragment extends Fragment {

    private static final String TAG = "ExpertMeetingFragment";
    @BindView(R.id.tv_location)
    TextInputEditText tvLocation;
    @BindView(R.id.tv_time)
    TextInputEditText tvTime;
    @BindView(R.id.tv_special_requirements)
    TextInputEditText tvSpecialRequirements;
    @BindView(R.id.singin)
    MaterialButton singin;

    @Inject
    FunctionViewModel viewModel;

    String expertId = "";
    @BindView(R.id.spin_kit)
    ProgressBar spinKit;

    //TODO add the datepicker convert to into string/dateformat

    @Inject
    public ExpertMeetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expert_meeting, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);

        expertId = getArguments().getString(Constants.APPOINTMENT_EXPERT_ID);

        subscribeObserverForAppointmentCreation();


        return view;
    }

    private void subscribeObserverForAppointmentCreation() {
        viewModel.getResponsePostAppointment().observe(this, aBoolean -> {
            if (aBoolean) {
                spinKit.setVisibility(View.INVISIBLE);
            }else{

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
        //TODO pick the date n time and send them in the body
        String location = tvLocation.getText().toString();


        viewModel.createAppointment(expertId, new FarmerAppointmentsBody(location, "somedate"));
        spinKit.setVisibility(View.VISIBLE);
    }
}
