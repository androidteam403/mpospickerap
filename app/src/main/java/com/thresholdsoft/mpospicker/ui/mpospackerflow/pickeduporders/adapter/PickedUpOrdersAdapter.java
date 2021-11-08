package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterPickedUpordersBinding;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersActivity;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersMvpView;

import java.util.List;

public class PickedUpOrdersAdapter extends RecyclerView.Adapter<PickedUpOrdersAdapter.ViewHolder> {

    private Activity activity;
    private List<PickedUpOrdersActivity.PickedUpFullfillmentData> fullfillmentDataList;
    private PickedUpOrdersMvpView pickedUpOrdersMvpView;


    public PickedUpOrdersAdapter(Activity activity, List<PickedUpOrdersActivity.PickedUpFullfillmentData> fullfillmentDataList, PickedUpOrdersMvpView pickedUpOrdersMvpView) {
        this.activity = activity;
        this.fullfillmentDataList = fullfillmentDataList;
        this.pickedUpOrdersMvpView = pickedUpOrdersMvpView;
    }

    @NonNull
    @Override
    public PickedUpOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterPickedUpordersBinding adapterPickedUpordersBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_picked_uporders, parent, false);
        return new PickedUpOrdersAdapter.ViewHolder(adapterPickedUpordersBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PickedUpOrdersAdapter.ViewHolder holder, int position) {
        PickedUpOrdersActivity.PickedUpFullfillmentData fullfillmentData = fullfillmentDataList.get(position);
        holder.adapterPickedUpordersBinding.filmentId.setText(fullfillmentData.getFullfilmentId());
        holder.adapterPickedUpordersBinding.totalItems.setText(String.valueOf(fullfillmentData.getTotalItems()));
        if (fullfillmentData.getOrderStatus() == 0) {
            holder.adapterPickedUpordersBinding.statusText.setText("Full");
            holder.adapterPickedUpordersBinding.fullStatusColor.setVisibility(View.VISIBLE);
        } else if (fullfillmentData.getOrderStatus() == 1) {
            holder.adapterPickedUpordersBinding.statusText.setText("Partial");
            holder.adapterPickedUpordersBinding.partialStatusColor.setVisibility(View.VISIBLE);
        } else if (fullfillmentData.getOrderStatus() == 2) {
            holder.adapterPickedUpordersBinding.statusText.setText("Not Available");
            holder.adapterPickedUpordersBinding.parent.setAlpha((float) 0.6);
            holder.adapterPickedUpordersBinding.parent.setBackgroundResource(R.color.light_grey);
            holder.adapterPickedUpordersBinding.notAvailableStatusColor.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullfillmentData.getOrderStatus() != 2) {
                    pickedUpOrdersMvpView.onItemClick(position, fullfillmentData.getOrderStatus());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fullfillmentDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterPickedUpordersBinding adapterPickedUpordersBinding;

        public ViewHolder(@NonNull AdapterPickedUpordersBinding adapterPickedUpordersBinding) {
            super(adapterPickedUpordersBinding.getRoot());
            this.adapterPickedUpordersBinding = adapterPickedUpordersBinding;
        }
    }
}
