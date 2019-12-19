package com.github.tenx.xcom.ui.business.funcnatilies.notification;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.functions.appointments.FarmerAppointmentsResponse;
import com.github.tenx.xcom.ui.business.funcnatilies.BusinessFunctionViewModel;
import com.github.tenx.xcom.ui.business.funcnatilies.notification.adapter.NotificationRecyclerAdapter;
import com.github.tenx.xcom.ui.business.funcnatilies.singleNotification.BusSingleNotificationFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {

    @Inject
    NotificationRecyclerAdapter adapter;

    private static final String TAG = "NotificationsFragment";

    ArrayList<FarmerAppointmentsResponse.AppointmentBody> itemList;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    BusinessFunctionViewModel viewModel;

    @Inject
    BusSingleNotificationFragment busSingleNotificationFragment;




    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Log.d(TAG, "onClick: POsition ::: " + position);

            //  goToNextActivity(position);

            initializeFragments(busSingleNotificationFragment);
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
    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this,view);

        setUpRecycler(recyclerView,adapter);



        subscribeObserversForAppointmentsList();
        subscribeObserversForAppointmentStatus();


        return view;
    }


    private void subscribeObserversForAppointmentStatus() {
//        viewModel().observe(this, aBoolean -> {
//            if (aBoolean){
//                progressBar.setVisibility(View.GONE);
//
//            }else{
//
//            }
//        });
    }

    //TODO check this

    private void subscribeObserversForAppointmentsList() {
//        viewModel.getAppointmentsForFarmer().observe(this, farmerAppointmentsResponse -> {
//            itemList = farmerAppointmentsResponse.getmList();
//
//            Log.d(TAG, "onChanged: " + itemList.size());
//
//            adapter.updateListItems(itemList);
//
//        });
    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    private void setUpRecycler(RecyclerView recyclerView, NotificationRecyclerAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);



    }




}
