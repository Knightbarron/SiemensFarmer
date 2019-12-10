package com.github.tenx.xcom.ui.Function.advertisements;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.ui.Function.advertisements.adapter.AdvertisementsAdapter;
import com.github.tenx.xcom.ui.Function.advertisements.adapter.AdvertisementsDataModel;
import com.github.tenx.xcom.ui.Function.articles.adapter.ArticlesAdapter;
import com.github.tenx.xcom.ui.Function.articles.adapter.ArticlesDataModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvertisementsFragment extends Fragment {

    private static final String TAG = "AdvertisementsFragment";


    @Inject
    AdvertisementsAdapter adapter;

    ArrayList<AdvertisementsDataModel> itemList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Inject
    public AdvertisementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advertisements, container, false);

        ButterKnife.bind(this,view);
        AndroidSupportInjection.inject(this);

        itemList = new ArrayList<>();


        setUpRecycler(recyclerView,adapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    private void setUpRecycler(RecyclerView recyclerView, AdvertisementsAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.updateListItems(loadItems());
    }

    private List<AdvertisementsDataModel> loadItems() {

        itemList.add(new AdvertisementsDataModel("Company 1 ", "Product 1", "Some description boyd",R.drawable.ic_my_location));
        itemList.add(new AdvertisementsDataModel("Company 2 ", "Product 11", "Some description boyd 22",R.drawable.ic_launcher_foreground));
        itemList.add(new AdvertisementsDataModel("Company 3 ", "Product 12", "Some description boyd33",R.drawable.ic_launcher_background));
        itemList.add(new AdvertisementsDataModel("Company 4 ", "Product 4", "Some description boyd44 ",R.drawable.ic_launcher_foreground));
        itemList.add(new AdvertisementsDataModel("Company 5 ", "Product 5", "Some description boyd 5",R.drawable.ic_launcher_background));
        return itemList;

    }


}
