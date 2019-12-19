package com.github.tenx.xcom.ui.auth.registration;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.auth.RegistrationBody;
import com.github.tenx.xcom.ui.auth.AuthViewModel;
import com.github.tenx.xcom.ui.auth.login.LoginFragment;
import com.github.tenx.xcom.ui.main.MainActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
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
public class RegistrationFragment extends Fragment {


    @BindView(R.id.email)
    TextInputEditText email;
    @BindView(R.id.password)
    TextInputEditText password;
    @BindView(R.id.spin_kit)
    ProgressBar spinKit;
    @BindView(R.id.register)
    MaterialButton register;
    @BindView(R.id.tv_login_text)
    TextView tvLoginText;
    @BindView(R.id.login)
    MaterialButton login;
    @BindView(R.id.parent_register_ll)
    LinearLayout layout;

    @Inject
    AuthViewModel viewModel;
    private static final String TAG = "RegistrationFragment";


    @BindView(R.id.tv_text_input_3)
    TextInputLayout tvTextInput3;
    @BindView(R.id.tv_text_input_l)
    TextInputLayout tvTextInputL;
    @BindView(R.id.tv_text_input_2)
    TextInputLayout tvTextInput2;
    @BindView(R.id.email_reg_form)
    LinearLayout emailRegForm;
    @BindView(R.id.login_form)
    ScrollView loginForm;
    @BindView(R.id.first_name)
    TextInputEditText firstName;
    @BindView(R.id.tv_text_input_4)
    TextInputLayout tvTextInput4;
    @BindView(R.id.last_name)
    TextInputEditText lastName;


    @Inject
    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);

        if (viewModel == null)
            Log.d(TAG, "onCreateView: ViewModel is null");
        else
            Log.d(TAG, "onCreateView: ViewModel not null");

        subscribeObserver();


        return view;
    }

    private void subscribeObserver() {
        viewModel.getRegistrationResponse().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Log.d(TAG, "onChanged: Registration Complete");
                    spinKit.setVisibility(View.INVISIBLE);
                    Snackbar.make(layout, "Registration Complete", Snackbar.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                } else {
                    Log.d(TAG, "onChanged: Loading ");
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }


    @OnClick({R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                doRegister();

                break;
            case R.id.login:
                goToLoginFragment();
                break;
        }
    }

    private void doRegister() {


        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();
        String firstNameStr = firstName.getText().toString();
        String lastNameStr = lastName.getText().toString();


        if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            Snackbar.make(layout, "Enter valid Email", Snackbar.LENGTH_LONG).show();
            return;
        }


        if (passwordStr.length() < 6) {
            Snackbar.make(layout, "Password should have atleast 6 literals", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(firstNameStr) ) {
            Snackbar.make(layout, "Enter First name", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(lastNameStr) ) {
            Snackbar.make(layout, "Enter Last name", Snackbar.LENGTH_LONG).show();
            return;
        }

        spinKit.setVisibility(View.VISIBLE);
        //TODO the registration
        //  viewModel.registerFarmer(new RegistrationBody(emailStr, passwordStr,fullnameStr));
     //   viewModel.registerFarmer(new RegistrationBody(emailStr, passwordStr));


        viewModel.registerFarmer(new RegistrationBody(emailStr,passwordStr,firstNameStr,lastNameStr));
    }

    //TODO shit code
    private void goToLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        String backStateName = loginFragment.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);

        transaction.replace(R.id.frame_layout, loginFragment);
        transaction.addToBackStack(backStateName);

        transaction.commit();

    }


}
