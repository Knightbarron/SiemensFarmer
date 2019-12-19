package com.github.tenx.xcom.ui.Services.equipments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.functions.equipments.EquipmentBody;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class EquipmentsAdapter extends RecyclerView.Adapter<EquipmentsAdapter.EquipmentsViewHolder> {

    private List<EquipmentBody> mList;

    private View.OnClickListener onItemClickListener;

    public void setOnItemClickListener(View.OnClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    @Inject
    public EquipmentsAdapter() {
        mList = new ArrayList<>();

    }

    @NonNull
    @Override
    public EquipmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_lending_equipments,parent,false);
        return new EquipmentsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentsViewHolder holder, int position) {

        //TODO image thingy
        // holder.productImage.setImageResource(mList.get(position));
        //TODO set the location???
       // holder.productLocation.setText(mList.get(position));
        holder.productPrice.setText(mList.get(position).getPrice());
        holder.productName.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    public void updateListData(List<EquipmentBody>  mList){
        this.mList.addAll(mList);
        notifyDataSetChanged();

    }

    public class EquipmentsViewHolder extends RecyclerView.ViewHolder {



        TextView productName;
        TextView productPrice;
        TextView productLocation;
        ImageView productImage;


        public EquipmentsViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setTag(this);
            productName = itemView.findViewById(R.id.tv_product);
            productPrice = itemView.findViewById(R.id.tv_price_main);
            productLocation = itemView.findViewById(R.id.tv_location);
            productImage = itemView.findViewById(R.id.iv_equipments_image);

            itemView.setOnClickListener(onItemClickListener);

        }
    }
}

//TODO make the click with the book button
