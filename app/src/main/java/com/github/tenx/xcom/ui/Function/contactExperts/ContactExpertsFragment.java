package com.github.tenx.xcom.ui.Function.contactExperts;


import android.Manifest;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.functions.appointments.AllExpertsResponse;
import com.github.tenx.xcom.data.models.functions.appointments.ExpertProfileBody;
import com.github.tenx.xcom.data.prefs.AppPreferencesHelper;
import com.github.tenx.xcom.ui.Function.FunctionViewModel;
import com.github.tenx.xcom.ui.Function.contactExperts.adapter.ContactExpertsAdapter;
import com.github.tenx.xcom.ui.Function.singleExpert.SingleExpertFragment;
import com.github.tenx.xcom.utils.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactExpertsFragment extends Fragment {

    private static final String TAG = "ContactExpertsFragment";

    @BindView(R.id.locationText)
    EditText locationText;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.btn_search)
    MaterialButton btnSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    ContactExpertsAdapter adapter;


    ArrayList<ExpertProfileBody> itemList;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;


    @Inject
    FunctionViewModel viewModel;

    @Inject
    SingleExpertFragment singleExpertFragment;
    @BindView(R.id.layout)
    LinearLayout layout;

    private FusedLocationProviderClient fusedLocationProviderClient;


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Log.d(TAG, "onClick: POsition ::: " + position);


            //  goToNextActivity(position);
            String idForTheExpert = itemList.get(position).getId();

            Bundle bundle = new Bundle();
            bundle.putString(Constants.SEND_ID_TO_SINGLE_EXPERT, idForTheExpert);
            singleExpertFragment.setArguments(bundle);

            initializeFragments(singleExpertFragment);

        }
    };

    private void initializeFragments(SingleExpertFragment frag) {

        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);

        transaction.replace(R.id.frame_layout, frag);
        transaction.addToBackStack(backStateName);

        transaction.commit();
    }


    @Inject
    public ContactExpertsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_experts, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);
        progressbar.setVisibility(View.INVISIBLE);


        subscribeObserverForListStatus();
        subscribeObserverForTheExpertList();


        return view;
    }

    private void subscribeObserverForTheExpertList() {
        viewModel.getAllExperts().observe(this, new Observer<AllExpertsResponse>() {
            @Override
            public void onChanged(AllExpertsResponse allExpertsResponse) {
                itemList = allExpertsResponse.getmList();
                Log.d(TAG, "onChanged: SIze of the Epxerts list::: " + itemList.size());
                adapter.updateListData(itemList);
            }
        });
    }

    private void subscribeObserverForListStatus() {
        viewModel.getStatusAllExperts().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressbar.setVisibility(View.GONE);
                    Log.d(TAG, "onChanged: Success in retriving the data");
                } else {

                }
            }
        });
    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }


    private void setUpRecycler(RecyclerView recyclerView, ContactExpertsAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);
    }


    @OnClick(R.id.btn_search)
    public void onViewClicked() {

        String locationaName = locationText.getText().toString();
        if (locationaName.isEmpty()){
            Snackbar.make(layout,"Please enter PlaceHolder location.",Snackbar.LENGTH_SHORT).show();
            return;
        }


        progressbar.setVisibility(View.VISIBLE);
        setUpRecycler(recyclerView, adapter);
        progressbar.setVisibility(View.GONE);
        viewModel.getAllExpertsList();

    }

    @OnClick(R.id.iv_location)
    public void onLocationClicked() {
        attemptPermission();

    }

    private void attemptPermission() {

        Dexter.withActivity(getActivity()).withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                attemptLocation();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();
    }

    private void attemptLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        try {

            Task location = fusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "onComplete: found location..");
                        Location currentLocation = (Location) task.getResult();
                        Log.d(TAG, "onComplete: LAtitude is : " + currentLocation.getLatitude() +
                                " Longitude  : " + currentLocation.getLongitude());

                        getAddressFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude());
                    } else {
                        Log.d(TAG, "onComplete: curretn location is null");
                        Toast.makeText(getActivity(), "Unable to get location", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        } catch (SecurityException e) {
            Log.d(TAG, "attemptLocation: Security Exception : " + e.getMessage());
        }
    }

    private void getAddressFromLocation(double latitude, double longitude) {

        Geocoder geocoder = new Geocoder(getContext(), Locale.ENGLISH);


        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses.size() > 0) {
                Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();

//                for(int i =0 ;i<fetchedAddress.getMaxAddressLineIndex();i++){
//
//                    strAddress.append(fetchedAddress.getLocality()).append(" ");
//                }

                Log.d(TAG, "getAddressFromLocation: LOCALITY IS : " + fetchedAddress.getLocality());

                locationText.setText(fetchedAddress.getLocality());


            } else {
                Log.d(TAG, "getAddressFromLocation: Searching for location");
            }


        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "getAddressFromLocation: COuld not get Address");
        }
    }


}
