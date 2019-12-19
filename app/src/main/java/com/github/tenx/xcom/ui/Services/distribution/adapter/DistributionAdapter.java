package com.github.tenx.xcom.ui.Services.distribution.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DistributionAdapter extends RecyclerView.Adapter<DistributionAdapter.StorageViewHolder> {


    private List<String> mList;


    private View.OnClickListener onItemClickListener;


    public void setOnItemClickListener(View.OnClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }




    @Inject
    public DistributionAdapter() {

        mList = new ArrayList<>();
    }

    @NonNull
    @Override
    public StorageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_distribution,parent,false );
        return  new StorageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StorageViewHolder holder, int position) {
//        holder.storageImage.setImageResource(mList.get(position).getImage());
//        holder.storageLocation.setText(mList.get(position).getLocation());
//        holder.storageName.setText(mList.get(position).getName());
//        holder.storagePrice.setText(mList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    public void updateListData(List<String>  data){
        mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();

    }


    public class StorageViewHolder extends RecyclerView.ViewHolder {

        TextView storagePrice;
        TextView storageProduct;


        public StorageViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setTag(this);
            storageProduct = itemView.findViewById(R.id.tv_product);
            storagePrice = itemView.findViewById(R.id.tv_price_main);

            itemView.setOnClickListener(onItemClickListener);

        }
    }
}
