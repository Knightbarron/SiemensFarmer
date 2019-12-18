package com.github.tenx.xcom.ui.Function.createEquipments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.functions.equipments.EquipmentBody;
import com.github.tenx.xcom.ui.Function.FunctionViewModel;
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
public class CreateEquipmentFragment extends Fragment {


    @BindView(R.id.tv_name)
    TextInputEditText tvName;
    @BindView(R.id.tv_price)
    TextInputEditText tvPrice;
    @BindView(R.id.tv_description)
    TextInputEditText tvDescription;
    @BindView(R.id.tv_stock)
    TextInputEditText tvStock;
    @BindView(R.id.line4)
    TextInputLayout line4;
    @BindView(R.id.iv_equipments_image)
    ImageView ivEquipmentsImage;
    @BindView(R.id.image_btn)
    MaterialButton imageBtn;
    @BindView(R.id.btn_upload)
    MaterialButton btnUpload;
    @BindView(R.id.parent_layout)
    ScrollView parentLayout;

    @Inject
    FunctionViewModel viewModel;
    @BindView(R.id.line1)
    TextInputLayout line1;
    @BindView(R.id.line2)
    TextInputLayout line2;
    @BindView(R.id.line3)
    TextInputLayout line3;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    public CreateEquipmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_equipment, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);

        subscribeObserver();

        return view;
    }

    private void subscribeObserver() {
        viewModel.statusCreateEquipment().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.GONE);
                    Snackbar.make(parentLayout, "Equipment created", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(parentLayout, "Some error occured", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @OnClick({R.id.image_btn, R.id.btn_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_btn:
                Snackbar.make(parentLayout, "Uploading image", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btn_upload:
                createEquipment();
                break;
        }
    }

    private void createEquipment() {

        String name = tvName.getText().toString();
        String price = tvPrice.getText().toString();
        String description = tvDescription.getText().toString();
        int stock = Integer.parseInt(tvStock.getText().toString());

        viewModel.createEquipment(new EquipmentBody(
                name, price, description, stock, true));


    }
}
