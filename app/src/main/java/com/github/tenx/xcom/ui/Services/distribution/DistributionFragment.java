package com.github.tenx.xcom.ui.Services.distribution;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.ui.Services.ServicesViewModel;
import com.github.tenx.xcom.ui.Services.distribution.adapter.DistributionAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class DistributionFragment extends Fragment {

    private static final String TAG = "DistributionFragment";


    @Inject
    DistributionAdapter adapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    ServicesViewModel viewModel;


    private List<CropPriceModel> mList;


    @Inject
    public DistributionFragment() {
        // Required empty public constructor
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Log.d(TAG, "onClick: POsition ::: " + position);

            //  goToNextActivity(position);

            //   initializeFragments(farmerSingleNotificationFragment);
        }
    };

    //TODO send the id in PlaceHolder bundle


    public void initializeFragments(Fragment frag) {
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);

        transaction.replace(R.id.frame_layout, frag);
        transaction.addToBackStack(backStateName);

        transaction.commit();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_distribution, container, false);

        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this,view);
      //  mList = new ArrayList<>();

        setUpRecycler(recyclerView,adapter);
        viewModel.getMyCropnPrice();
        subscribeObserverForCropsnPrice();

      //  Log.d(TAG, "onCreateView: reached here" );

        return view;
    }

    private void subscribeObserverForCropsnPrice() {
        viewModel.getHashResponseForCrops().observe(this, new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> map) {
                Iterator hmIterator = map.entrySet().iterator();
                Log.d(TAG, "onChanged: Map size::: " + map.size());


                while (hmIterator.hasNext()) {
                    Map.Entry mapElement = (Map.Entry)hmIterator.next();
                    int price = ((int)mapElement.getValue());
                    String crop = (String) mapElement.getKey();

                    Log.d(TAG, "onChanged: crop" + crop +" price: " + price);

                    mList.add(new CropPriceModel(crop,price));

                }

                Log.d(TAG, "onChanged: list size::: " + mList.size());
                adapter.updateListData(mList);

            }
        });
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }


    private void setUpRecycler(RecyclerView recyclerView, DistributionAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);

      //  adapter.updateListData(loadItems());
    }



}
