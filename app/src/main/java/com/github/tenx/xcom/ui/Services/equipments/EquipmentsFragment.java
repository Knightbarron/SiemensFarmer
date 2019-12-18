package com.github.tenx.xcom.ui.Services.equipments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.functions.equipments.AllEquipmentsResponse;
import com.github.tenx.xcom.data.models.functions.equipments.EquipmentBody;
import com.github.tenx.xcom.ui.Services.ServicesViewModel;
import com.github.tenx.xcom.ui.Services.equipments.adapter.EquipmentsAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class EquipmentsFragment extends Fragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    EquipmentsAdapter adapter;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    ServicesViewModel viewModel;

    private List<EquipmentBody> itemList;

    //TODO set up the book button.. send the id with a bundle



    @Inject
    public EquipmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_equipments, container, false);
        ButterKnife.bind(this, view);
        AndroidSupportInjection.inject(this);
        setUpRecycler(recyclerView, adapter);

        subscribeObserverStatus();
        subscribeObserverDataList();




        return view;
    }

    private void subscribeObserverDataList() {
        viewModel.getAllEquipments().observe(this, new Observer<AllEquipmentsResponse>() {
            @Override
            public void onChanged(AllEquipmentsResponse allEquipmentsResponse) {
                itemList = allEquipmentsResponse.getmList();

                adapter.updateListData(itemList);

            }
        });
    }

    private void subscribeObserverStatus() {
        viewModel.getStatusAllEquipments().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    progressBar.setVisibility(View.GONE);
                }else{


                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }


    private void setUpRecycler(RecyclerView recyclerView, EquipmentsAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }





}
