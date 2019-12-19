package com.github.tenx.xcom.ui.Function.profile;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

    @BindView(R.id.first_name)
    TextInputEditText firstName;
    @BindView(R.id.line1)
    TextInputLayout line1;
    @BindView(R.id.last_name)
    TextInputEditText lastName;
    @BindView(R.id.line2)
    TextInputLayout line2;
    @BindView(R.id.location)
    TextInputEditText location;
    @BindView(R.id.line3)
    TextInputLayout line3;
    @BindView(R.id.bio)
    TextInputEditText bio;
    @BindView(R.id.line4)
    TextInputLayout line4;
    @BindView(R.id.tv_headline)
    TextView tvHeadline;
    @BindView(R.id.iv_topic)
    ImageView ivTopic;
    @BindView(R.id.tv_topic)
    TextView tvTopic;
    @BindView(R.id.crop)
    TextInputEditText crop;
    @BindView(R.id.line5)
    TextInputLayout line5;
    @BindView(R.id.add_crop_layout)
    LinearLayout addCropLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.singin)
    MaterialButton singin;
    @BindView(R.id.layout)
    ScrollView layout;

    @Inject
    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this,view);

        return view;
    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @OnClick({R.id.to_display, R.id.singin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.to_display:
                addANewCrop();
                break;

            case R.id.iv_topic:
                addANewCrop();
                break;
            case R.id.tv_topic:
                addANewCrop();
                break;
            case R.id.singin:

                patchProfileData();
                break;
        }
    }

    private void patchProfileData() {
        String strFirstName  = firstName.getText().toString();
        String strLastName = lastName.getText().toString();
        String strLocation = location.getText().toString();
        String strBio = bio.getText().toString();



    }

    private void addANewCrop() {

        addCropLayout.setVisibility(View.VISIBLE);

    }
}
