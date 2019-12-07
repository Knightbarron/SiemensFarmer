package com.github.tenx.xcom.ui.Function.contactExperts;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.tenx.xcom.R;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactExpertsFragment extends Fragment {

    @Inject
    public ContactExpertsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_experts, container, false);
    }

}
