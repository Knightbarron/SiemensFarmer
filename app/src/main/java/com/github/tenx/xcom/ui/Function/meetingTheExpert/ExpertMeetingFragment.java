package com.github.tenx.xcom.ui.Function.meetingTheExpert;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.functions.appointments.FarmerAppointmentsBody;
import com.github.tenx.xcom.data.prefs.AppPreferencesHelper;
import com.github.tenx.xcom.ui.Function.FunctionViewModel;
import com.github.tenx.xcom.utils.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
    @BindView(R.id.tv_special_requirements)
    TextInputEditText tvSpecialRequirements;
    @BindView(R.id.singin)
    MaterialButton bookAppointment;

    @Inject
    FunctionViewModel viewModel;

    String expertId = "";
    @BindView(R.id.spin_kit)
    ProgressBar spinKit;
    @BindView(R.id.tv_pick_time)
    TextView tvPickTime;
    @BindView(R.id.show_tv_time)
    TextView showTvTime;
    @BindView(R.id.tv_Pick_date)
    TextView tvPickDate;
    @BindView(R.id.showDate)
    TextView showDate;


    DatePickerDialog datePickerDialog;
    @BindView(R.id.line1)
    TextInputLayout line1;
    @BindView(R.id.line3)
    TextInputLayout line3;
    @BindView(R.id.simpleTimePicker)
    TimePicker simpleTimePicker;

    //TEST DATE N Time
    String date_time = "2019-12-16T13:56:47.805Z";
    @BindView(R.id.layout)
    LinearLayout layout;

    AppPreferencesHelper appPreferencesHelper;


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

     //   Log.d(TAG, "onCreateView :  Access Toekn::: " + appPreferencesHelper.getAccessToken());


        return view;
    }

    private void subscribeObserverForAppointmentCreation() {
        viewModel.getResponsePostAppointment().observe(this, aBoolean -> {
            if (aBoolean) {
                spinKit.setVisibility(View.INVISIBLE);
                Snackbar.make(layout,"Success",Snackbar.LENGTH_SHORT).show();
            } else {
                spinKit.setVisibility(View.GONE);
                Snackbar.make(layout,"Failure",Snackbar.LENGTH_SHORT).show();
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
        String descrip = tvSpecialRequirements.getText().toString();
//        OffsetDateTime now = null;
//        DateTimeFormatter formatter = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            formatter = DateTimeFormatter.ISO_DATE_TIME;
//            now = OffsetDateTime.now();
//            System.out.println(formatter.format(now));
//            Log.d(TAG, "onViewClicked: TIe::: " + formatter.format(now));
//        }


        viewModel.createAppointment(expertId, new FarmerAppointmentsBody(location, 1000, descrip));

        spinKit.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.tv_pick_time, R.id.tv_Pick_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pick_time:
                timePicking();
                break;
            case R.id.tv_Pick_date:
                datePicking();
                break;
        }
    }

    private void timePicking() {

        Calendar mcurrentTime = Calendar.getInstance();

        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                showTvTime.setText(selectedHour + ":" + selectedMinute);

            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }


    private void datePicking() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        showDate.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }
}
