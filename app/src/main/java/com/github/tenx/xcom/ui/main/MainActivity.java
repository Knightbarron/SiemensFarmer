package com.github.tenx.xcom.ui.main;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.ui.Function.FunctionActivity;
import com.github.tenx.xcom.ui.Services.ServicesActivity;
import com.github.tenx.xcom.ui.main.recyclerView.HomePageAdapter;
import com.github.tenx.xcom.ui.main.recyclerView.HomePageItemsModel;
import com.github.tenx.xcom.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import maes.tech.intentanim.CustomIntent;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";


    @Inject
    MainViewModel mainViewModel;

    @Inject
    HomePageAdapter adapter;



    ArrayList<HomePageItemsModel> itemList;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Log.d(TAG, "onClick: POsition ::: " + position);

            goToNextActivity(position);
            
            if (mainViewModel==null)
                Log.d(TAG, "onClick: VIEWMODEL IS EMPTY");
            else
                Log.d(TAG, "onClick: VIEWMODEL IS NOT EMPTY");

        }
    };


    private void goToNextActivity(int position) {
        if (position==0){
            Intent intent = new Intent(this, ServicesActivity.class);
            startActivity(intent);
            CustomIntent.customType(this,"left-to-right");
            return;
        }

        Intent intent = new Intent(this, FunctionActivity.class);
        intent.putExtra(Constants.SELECTED_ID,position);
        startActivity(intent);

        CustomIntent.customType(this,"left-to-right");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AndroidInjection.inject(this);
//        this is neccesary bind call for BindView decorators
        ButterKnife.bind(this);


        setUpRecycler(recyclerView,adapter);


        if (mainViewModel == null) {
            Timber.d("View model NOT ok");
        } else {
            Timber.d("View model is locked and loaded!");
        }


    }


    private void setUpRecycler(RecyclerView recyclerView, HomePageAdapter adapter) {
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);
        adapter.updateListItems(loadItems());
    }

    private List<HomePageItemsModel> loadItems() {
        itemList = new ArrayList<>();
        itemList.add(new HomePageItemsModel(R.drawable.ic_launcher_foreground,"Services"));
        itemList.add(new HomePageItemsModel(R.drawable.ic_launcher_foreground,"Predict my Production"));
        itemList.add(new HomePageItemsModel(R.drawable.ic_launcher_foreground,"Shop"));
        itemList.add(new HomePageItemsModel(R.drawable.ic_launcher_foreground,"Post a Question"));
        itemList.add(new HomePageItemsModel(R.drawable.ic_launcher_foreground,"Contact the Experts"));
        itemList.add(new HomePageItemsModel(R.drawable.ic_launcher_foreground,"Articles"));
        return itemList;
    }

}
