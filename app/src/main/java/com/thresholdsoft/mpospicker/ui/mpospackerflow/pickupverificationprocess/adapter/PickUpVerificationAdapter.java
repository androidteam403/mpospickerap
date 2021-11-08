package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterPickupVerificationBinding;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationActivity;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationMvpView;

import java.util.List;

public class PickUpVerificationAdapter extends RecyclerView.Adapter<PickUpVerificationAdapter.ViewHolder> {

    private Activity activity;
    private List<PickUpVerificationActivity.PickPackProductsData> pickPackProductsDataList;
    private PickUpVerificationMvpView pickUpVerificationMvpView;


    public PickUpVerificationAdapter(Activity activity, List<PickUpVerificationActivity.PickPackProductsData> pickPackProductsDataList, PickUpVerificationMvpView pickUpVerificationMvpView) {
        this.activity = activity;
        this.pickPackProductsDataList = pickPackProductsDataList;
        this.pickUpVerificationMvpView = pickUpVerificationMvpView;
    }

    @NonNull
    @Override
    public PickUpVerificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterPickupVerificationBinding adapterPickupVerificationBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_pickup_verification, parent, false);
        return new PickUpVerificationAdapter.ViewHolder(adapterPickupVerificationBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PickUpVerificationAdapter.ViewHolder holder, int position) {
        PickUpVerificationActivity.PickPackProductsData pickPackProductsData = pickPackProductsDataList.get(position);
        holder.adapterPickupVerificationBinding.products.setText(pickPackProductsData.getProduct());
        holder.adapterPickupVerificationBinding.qty.setText(String.valueOf(pickPackProductsData.getQty()));
        if (pickPackProductsData.getProductStatus() == 0) {
            holder.adapterPickupVerificationBinding.fullStatusColor.setVisibility(View.VISIBLE);
        } else if (pickPackProductsData.getProductStatus() == 1) {
            holder.adapterPickupVerificationBinding.partialStatusColor.setVisibility(View.VISIBLE);
        } else if (pickPackProductsData.getProductStatus() == 2) {
            holder.adapterPickupVerificationBinding.notAvailableStatusColor.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pickUpVerificationMvpView.recyclerItemClickableStatus()) {
                    pickUpVerificationMvpView.onItemClick(position, pickPackProductsData);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pickPackProductsDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterPickupVerificationBinding adapterPickupVerificationBinding;

        public ViewHolder(@NonNull AdapterPickupVerificationBinding adapterPickupVerificationBinding) {
            super(adapterPickupVerificationBinding.getRoot());
            this.adapterPickupVerificationBinding = adapterPickupVerificationBinding;
        }
    }
}
