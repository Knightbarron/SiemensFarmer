package com.github.tenx.xcom.ui.Function.advertisements.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.ui.Function.articles.adapter.ArticlesDataModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AdvertisementsAdapter extends RecyclerView.Adapter<AdvertisementsAdapter.AdvertisementViewHolder> {

    private List<AdvertisementsDataModel> mList;


    @Inject
    public AdvertisementsAdapter() {
        mList = new ArrayList<>();
    }


    @NonNull
    @Override
    public AdvertisementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_advertisements,parent,false);
        return new AdvertisementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertisementViewHolder holder, int position) {

        holder.tvComapny.setText(mList.get(position).getNameCompany());
        holder.tvProduct.setText(mList.get(position).getNameProduct());
        holder.tvDescription.setText(mList.get(position).getAdBody());
        holder.bodyImage.setImageResource(mList.get(position).getAdImage());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateListItems(List<AdvertisementsDataModel> mList){
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }


    public class AdvertisementViewHolder extends RecyclerView.ViewHolder {

        private TextView tvComapny;
        private TextView tvProduct;
        private TextView tvDescription;
        private ImageView bodyImage;


        public AdvertisementViewHolder(@NonNull View itemView) {
            super(itemView);

            tvComapny = itemView.findViewById(R.id.tv_title);
            tvProduct = itemView.findViewById(R.id.tv_headline);
            tvDescription = itemView.findViewById(R.id.tv_desceription);
            bodyImage = itemView.findViewById(R.id.iv_advertisement_image);


        }
    }
}
