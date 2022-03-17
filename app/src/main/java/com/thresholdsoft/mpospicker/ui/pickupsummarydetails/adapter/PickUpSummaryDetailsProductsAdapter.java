package com.thresholdsoft.mpospicker.ui.pickupsummarydetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterPickupSummaryDetailsProductsBinding;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public class PickUpSummaryDetailsProductsAdapter extends RecyclerView.Adapter<PickUpSummaryDetailsProductsAdapter.ViewHolder> {
    public List<RacksDataResponse.FullfillmentDetail.Product> racksDataResponse;
    public Context context;

    public PickUpSummaryDetailsProductsAdapter(Context context, List<RacksDataResponse.FullfillmentDetail.Product> racksDataResponse) {
        this.context = context;
        this.racksDataResponse = racksDataResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterPickupSummaryDetailsProductsBinding pickupSummaryDetailsProductsBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_pickup_summary_details_products, parent, false);
        return new PickUpSummaryDetailsProductsAdapter.ViewHolder(pickupSummaryDetailsProductsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RacksDataResponse.FullfillmentDetail.Product dataResponse = racksDataResponse.get(position);
        holder.pickupSummaryDetailsProductsBinding.productName.setText(dataResponse.getProductName());
        holder.pickupSummaryDetailsProductsBinding.rackId.setText(dataResponse.getRackId());
        holder.pickupSummaryDetailsProductsBinding.batchNo.setText(dataResponse.getBatchId());
    }

    @Override
    public int getItemCount() {
        return racksDataResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public AdapterPickupSummaryDetailsProductsBinding pickupSummaryDetailsProductsBinding;

        public ViewHolder(@NonNull AdapterPickupSummaryDetailsProductsBinding pickupSummaryDetailsProductsBinding) {
            super(pickupSummaryDetailsProductsBinding.getRoot());
            this.pickupSummaryDetailsProductsBinding = pickupSummaryDetailsProductsBinding;
        }
    }
}
