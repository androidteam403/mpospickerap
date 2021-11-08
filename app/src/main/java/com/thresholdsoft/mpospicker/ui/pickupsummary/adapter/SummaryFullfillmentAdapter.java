package com.thresholdsoft.mpospicker.ui.pickupsummary.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterSummaryFullfillmentBinding;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummaryActivity;

import java.util.List;

public class SummaryFullfillmentAdapter extends RecyclerView.Adapter<SummaryFullfillmentAdapter.ViewHolder> {

    private Activity activity;
    private List<PickUpSummaryActivity.SummaryFullfillmentData> pickPackProductsDataList;
    private PickUpSummaryActivity mvpView;


    public SummaryFullfillmentAdapter(Activity activity, List<PickUpSummaryActivity.SummaryFullfillmentData> pickPackProductsDataList, PickUpSummaryActivity mvpView) {
        this.activity = activity;
        this.pickPackProductsDataList = pickPackProductsDataList;
        this.mvpView = mvpView;
    }

    @NonNull
    @Override
    public SummaryFullfillmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterSummaryFullfillmentBinding adapterFullfilmentBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_summary_fullfillment, parent, false);
        return new SummaryFullfillmentAdapter.ViewHolder(adapterFullfilmentBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SummaryFullfillmentAdapter.ViewHolder holder, int position) {
        PickUpSummaryActivity.SummaryFullfillmentData pickPackProductsData = pickPackProductsDataList.get(position);
        holder.adapterFullfilmentBinding.filmentId.setText(pickPackProductsData.getFullfilmentId());
        holder.adapterFullfilmentBinding.totalItems.setText(String.valueOf(pickPackProductsData.getTotalItems()));
        holder.adapterFullfilmentBinding.boxId.setText(String.valueOf(pickPackProductsData.getBoxId()));
        if (pickPackProductsData.getOrderStatus()==0){
            holder.adapterFullfilmentBinding.fullStatusColor.setVisibility(View.VISIBLE);
            holder.adapterFullfilmentBinding.statusText.setText("Full");
        }else if (pickPackProductsData.getOrderStatus()==1){
            holder.adapterFullfilmentBinding.partialStatusColor.setVisibility(View.VISIBLE);
            holder.adapterFullfilmentBinding.statusText.setText("Partial");
        }else if (pickPackProductsData.getOrderStatus()==2){
            holder.adapterFullfilmentBinding.notAvailableStatusColor.setVisibility(View.VISIBLE);
            holder.adapterFullfilmentBinding.statusText.setText("Not Available");
        }
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
        AdapterSummaryFullfillmentBinding adapterFullfilmentBinding;

        public ViewHolder(@NonNull AdapterSummaryFullfillmentBinding adapterFullfilmentBinding) {
            super(adapterFullfilmentBinding.getRoot());
            this.adapterFullfilmentBinding = adapterFullfilmentBinding;
        }
    }
}
