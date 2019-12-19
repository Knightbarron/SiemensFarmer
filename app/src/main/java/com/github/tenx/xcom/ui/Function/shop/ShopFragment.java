package com.github.tenx.xcom.ui.Function.shop;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.products.ProductsBody;
import com.github.tenx.xcom.ui.Function.shop.adapter.ShopAdapter;
import com.github.tenx.xcom.ui.Function.singleItemShop.SingleItemShopFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {

    private static final String TAG = "ShopFragment";


    @Inject
    ShopAdapter adapter;

    ArrayList<ProductsBody> itemList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    SingleItemShopFragment singleItemShopFragment;


    @Inject
    public ShopFragment() {
        // Required empty public constructor
    }



    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Log.d(TAG, "onClick: POsition ::: " + position);

            String id = "";
            initializeFragments(singleItemShopFragment,id);

        }
    };

    public void initializeFragments( Fragment frag,String s){
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right);

        transaction.replace(R.id.frame_layout,frag);
        transaction.addToBackStack(backStateName);

        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

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

    private void setUpRecycler(RecyclerView recyclerView, ShopAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);

    }



}
