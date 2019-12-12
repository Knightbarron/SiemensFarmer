package com.github.tenx.xcom.ui.auth.login;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.ui.auth.registration.RegistrationFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.email)
    TextInputEditText email;
    @BindView(R.id.password)
    TextInputEditText password;
    @BindView(R.id.singin)
    MaterialButton singin;
    @BindView(R.id.register)
    MaterialButton register;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;

    @Inject
    RegistrationFragment registrationFragment;
    @BindView(R.id.layout)
    LinearLayout layout;


    @Inject
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);


        return view;
    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }


    @OnClick({R.id.singin, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.singin:
                signIn();
                break;
            case R.id.register:
                initializeFrag(registrationFragment);
                break;
        }
    }

    public void initializeFrag(Fragment frag) {
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);

        transaction.replace(R.id.frame_layout, frag);
        transaction.addToBackStack(backStateName);

        transaction.commit();
    }


    public void signIn() {

        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            Snackbar.make(layout, "Enter valid Email", Snackbar.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(passwordStr) || passwordStr.length() < 6) {
            Snackbar.make(layout, "Password should have atleast 6 literals", Snackbar.LENGTH_LONG).show();
            return;
        }

        //TODO the signIn



    }

}
