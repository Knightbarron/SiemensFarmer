package com.github.tenx.xcom.ui.Function.singleExpert;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.functions.appointments.ExpertProfileBody;
import com.github.tenx.xcom.ui.Function.FunctionViewModel;
import com.github.tenx.xcom.ui.Function.meetingTheExpert.ExpertMeetingFragment;
import com.github.tenx.xcom.utils.Constants;
import com.google.android.material.button.MaterialButton;

import java.util.function.Function;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleExpertFragment extends Fragment {


    private static final String TAG = "SingleExpertFragment";

    @BindView(R.id.iv_advertisement_image)
    ImageView ivAdvertisementImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_headline)
    TextView tvHeadline;
    @BindView(R.id.tv_desceription)
    TextView tvDesceription;
    @BindView(R.id.btn_confirmBooking)
    MaterialButton btnConfirmBooking;

    @Inject
    ExpertMeetingFragment expertMeetingFragment;


    String singleExpertId = "";
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    FunctionViewModel viewModel;

    @Inject
    public SingleExpertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_expert, container, false);

        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.GONE);



        singleExpertId = getArguments().getString(Constants.SEND_ID_TO_SINGLE_EXPERT);

        Log.d(TAG, "onCreateView: ID of the single Expert clicked::: " + singleExpertId);

        //TODO retrieve expert profile

        viewModel.retrieveSingleExpertProfile(singleExpertId);
        subscribeObserverForProfileStatus();
        subscribeObserverForPRofileData();

        return view;
    }

    private void subscribeObserverForPRofileData() {
        viewModel.getSingleExpert().observe(this, new Observer<ExpertProfileBody>() {
            @Override
            public void onChanged(ExpertProfileBody expertProfileBody) {

                if (expertProfileBody.getId()!=null)
                    tvTitle.setText(expertProfileBody.getId());

                String fName = expertProfileBody.getFirstName();
                String lName = expertProfileBody.getLastName();
                String name = fName+" " + lName;

                    tvTitle.setText(name);
                if (expertProfileBody.getExpertise()!=null)
                    tvHeadline.setText(expertProfileBody.getExpertise());
                if (expertProfileBody.getDescription()!=null)
                    tvDesceription.setText(expertProfileBody.getDescription());
                //TODO set up the Image thing
            }
        });
    }

    private void subscribeObserverForProfileStatus() {
        viewModel.getStatusSingleExpertProfile().observe(this, new Observer<Boolean>() {
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

    @OnClick(R.id.btn_confirmBooking)
    public void onViewClicked() {

        String backStateName = expertMeetingFragment.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);


        //TODO get the id of the expert and send it in PlaceHolder bundle

        Bundle bundle = new Bundle();
        bundle.putString(Constants.APPOINTMENT_EXPERT_ID, singleExpertId);
        expertMeetingFragment.setArguments(bundle);

        transaction.replace(R.id.frame_layout, expertMeetingFragment);
        transaction.addToBackStack(backStateName);

        transaction.commit();

    }
}
