package com.thresholdsoft.mpospicker.ui.pickupsummarydetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.PickupsummaryAdapterBinding;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public class NewPickUpSummaryAdapter extends RecyclerView.Adapter<NewPickUpSummaryAdapter.ViewHolder> {
    public List<RacksDataResponse.FullfillmentDetail.Product> racksDataResponse;
    public Context context;

    public NewPickUpSummaryAdapter(Context context, List<RacksDataResponse.FullfillmentDetail.Product> racksDataResponse) {
        this.context = context;

        this.racksDataResponse = racksDataResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PickupsummaryAdapterBinding pickupsummaryAdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.pickupsummary_adapter, parent, false);
        return new NewPickUpSummaryAdapter.ViewHolder(pickupsummaryAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RacksDataResponse.FullfillmentDetail.Product    dataResponse = racksDataResponse.get(position);
        holder.pickupsummaryAdapterBinding.productName.setText(dataResponse.getProductName());
        holder.pickupsummaryAdapterBinding.rackId.setText(dataResponse.getRackId());
        holder.pickupsummaryAdapterBinding.BatchNo.setText(dataResponse.getBatchId());
    }

    @Override
    public int getItemCount() {
        return racksDataResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public PickupsummaryAdapterBinding pickupsummaryAdapterBinding;

        public ViewHolder(@NonNull PickupsummaryAdapterBinding pickupsummaryAdapterBinding) {
            super(pickupsummaryAdapterBinding.getRoot());
            this.pickupsummaryAdapterBinding = pickupsummaryAdapterBinding;
        }
    }
}
