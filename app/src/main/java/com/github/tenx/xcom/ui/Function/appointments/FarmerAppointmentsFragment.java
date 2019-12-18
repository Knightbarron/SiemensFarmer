package com.github.tenx.xcom.ui.Function.appointments;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.functions.appointments.FarmerAppointmentsResponse;
import com.github.tenx.xcom.ui.Function.FunctionViewModel;
import com.github.tenx.xcom.ui.Function.appointments.adapter.FarmerAppointmentsAdapter;
import com.github.tenx.xcom.ui.Function.singleNotification.FarmerSingleNotificationFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class FarmerAppointmentsFragment extends Fragment {

    private static final String TAG = "FarmerAppointmentsFragm";

    @Inject
    FarmerAppointmentsAdapter adapter;

    ArrayList<FarmerAppointmentsResponse.AppointmentBody> itemList;

    @Inject
    FarmerSingleNotificationFragment farmerSingleNotificationFragment;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    FunctionViewModel viewModel;


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Log.d(TAG, "onClick: POsition ::: " + position);

            //  goToNextActivity(position);

            initializeFragments(farmerSingleNotificationFragment);
        }
    };

    //TODO send the id in a bundle


    public void initializeFragments(Fragment frag) {
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);

        transaction.replace(R.id.frame_layout, frag);
        transaction.addToBackStack(backStateName);

        transaction.commit();
    }


    @Inject
    public FarmerAppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_farmer_appointments, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);


        setUpRecycler(recyclerView,adapter);
        viewModel.getAllAppointments();
        viewModel.getStatusFarmerrAppointmentsResponse();

        subscribeObserversForAppointmentsList();
        subscribeObserversForAppointmentStatus();



        return view;
    }

    private void subscribeObserversForAppointmentStatus() {
        viewModel.getStatusFarmerrAppointmentsResponse().observe(this, aBoolean -> {
            if (aBoolean){
                progressBar.setVisibility(View.GONE);

            }else{

            }
        });
    }

    //TODO check this

    private void subscribeObserversForAppointmentsList() {
        viewModel.getAppointmentsForFarmer().observe(this, farmerAppointmentsResponse -> {
            itemList = farmerAppointmentsResponse.getmList();

            Log.d(TAG, "onChanged: " + itemList.size());

            adapter.updateListItems(itemList);

        });
    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    private void setUpRecycler(RecyclerView recyclerView, FarmerAppointmentsAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);

    }



}
