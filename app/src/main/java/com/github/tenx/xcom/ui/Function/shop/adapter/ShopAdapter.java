package com.github.tenx.xcom.ui.Function.shop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tenx.xcom.R;
import com.github.tenx.xcom.data.models.products.ProductsBody;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.AdvertisementViewHolder> {

    private List<ProductsBody> mList;
    private View.OnClickListener onItemClickListener;


    @Inject
    public ShopAdapter() {
        mList = new ArrayList<>();
    }


    @NonNull
    @Override
    public AdvertisementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_advertisements,parent,false);
        return new AdvertisementViewHolder(view);
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertisementViewHolder holder, int position) {

        holder.tvPrice.setText(mList.get(position).getPrice());
        holder.tvProduct.setText(mList.get(position).getName());
        holder.tvDescription.setText(mList.get(position).getDescription());
        //holder.bodyImage.setImageResource(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateListItems(List<ProductsBody> mList){
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }


    public class AdvertisementViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProduct;
        private TextView tvPrice;
        private TextView tvDescription;
        private ImageView bodyImage;


        public AdvertisementViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setTag(this);

            tvProduct = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_desceription);
            bodyImage = itemView.findViewById(R.id.iv_advertisement_image);
            tvPrice = itemView.findViewById(R.id.tv_price_main);

            itemView.setOnClickListener(onItemClickListener);


        }
    }
}
