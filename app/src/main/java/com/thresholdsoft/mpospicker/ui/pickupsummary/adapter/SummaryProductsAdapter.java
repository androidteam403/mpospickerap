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
import com.thresholdsoft.mpospicker.databinding.AdapterSummaryProductsBinding;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummaryActivity;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummaryMvpView;

import java.util.List;

public class SummaryProductsAdapter extends RecyclerView.Adapter<SummaryProductsAdapter.ViewHolder> {

    private Activity activity;
    private List<PickUpSummaryActivity.SummaryProductsData> pickPackProductsDataList;
    private PickUpSummaryMvpView mvpView;


    public SummaryProductsAdapter(Activity activity, List<PickUpSummaryActivity.SummaryProductsData> pickPackProductsDataList, PickUpSummaryMvpView mvpView) {
        this.activity = activity;
        this.pickPackProductsDataList = pickPackProductsDataList;
        this.mvpView = mvpView;
    }

    @NonNull
    @Override
    public SummaryProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterSummaryProductsBinding adapterSummaryProductsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_summary_products, parent, false);
        return new SummaryProductsAdapter.ViewHolder(adapterSummaryProductsBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SummaryProductsAdapter.ViewHolder holder, int position) {
        PickUpSummaryActivity.SummaryProductsData pickPackProductsData = pickPackProductsDataList.get(position);
        holder.adapterSummaryProductsBinding.products.setText(pickPackProductsData.getProduct());
        holder.adapterSummaryProductsBinding.qty.setText(pickPackProductsData.getQty());

        if (pickPackProductsData.getProductStatus() == 0) {
            holder.adapterSummaryProductsBinding.fullStatusColor.setVisibility(View.VISIBLE);
        } else if (pickPackProductsData.getProductStatus() == 1) {
            holder.adapterSummaryProductsBinding.partialStatusColor.setVisibility(View.VISIBLE);
        } else if (pickPackProductsData.getProductStatus() == 2) {
            holder.adapterSummaryProductsBinding.notAvailableStatusColor.setVisibility(View.VISIBLE);
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
        AdapterSummaryProductsBinding adapterSummaryProductsBinding;

        public ViewHolder(@NonNull AdapterSummaryProductsBinding adapterSummaryProductsBinding) {
            super(adapterSummaryProductsBinding.getRoot());
            this.adapterSummaryProductsBinding = adapterSummaryProductsBinding;
        }
    }
}