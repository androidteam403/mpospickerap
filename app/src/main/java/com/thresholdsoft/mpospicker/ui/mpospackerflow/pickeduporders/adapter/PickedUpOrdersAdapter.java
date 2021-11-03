package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterPickedUpordersBinding;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpActivity;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpMvpView;

import java.util.List;

public class PickedUpOrdersAdapter extends RecyclerView.Adapter<PickedUpOrdersAdapter.ViewHolder> {

    private Activity activity;
    private List<ReadyForPickUpActivity.FullfillmentData> fullfillmentDataList;
    private ReadyForPickUpMvpView readyForPickUpMvpView;


    public PickedUpOrdersAdapter(Activity activity, List<ReadyForPickUpActivity.FullfillmentData> fullfillmentDataList, ReadyForPickUpMvpView readyForPickUpMvpView) {
        this.activity = activity;
        this.fullfillmentDataList = fullfillmentDataList;
        this.readyForPickUpMvpView = readyForPickUpMvpView;
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
        ReadyForPickUpActivity.FullfillmentData fullfillmentData = fullfillmentDataList.get(position);
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
