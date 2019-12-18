package com.github.tenx.xcom.ui.business.funcnatilies.singleNotification;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.tenx.xcom.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusSingleNotificationFragment extends Fragment {

    @BindView(R.id.textViewOptions)
    ImageView textViewOptions;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.tv_decline)
    TextView tvDecline;
    @BindView(R.id.tv_accept)
    TextView tvAccept;

    @Inject
    public BusSingleNotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bus_single_notification, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this,view);


        return view;
    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }


    @OnClick({R.id.tv_decline, R.id.tv_accept})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_decline:
                declineAppointment();
                hideButtons();
                break;
            case R.id.tv_accept:
                acceptAppointment();
                hideButtons();
                break;
        }
    }

    private void hideButtons() {
        tvDecline.setVisibility(View.GONE);
        tvAccept.setVisibility(View.GONE);
    }


    private void acceptAppointment() {
        //todo network call

    }

    private void declineAppointment() {
        //TODO network call

    }
}
