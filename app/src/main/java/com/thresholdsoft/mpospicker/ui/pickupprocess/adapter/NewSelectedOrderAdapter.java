package com.thresholdsoft.mpospicker.ui.pickupprocess.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterPickupSummaryDetailsProductsBinding;
import com.thresholdsoft.mpospicker.databinding.AdapterSelectedPickupProcessProductsBinding;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessMvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public class NewSelectedOrderAdapter extends RecyclerView.Adapter<NewSelectedOrderAdapter.ViewHolder> {
    private PickupProcessMvpView pickupProcessMvpView;
    private boolean isRackFlow;
    String fullfillmentId;
    List<List<RackAdapter.RackBoxModel.ProductData>> listOfList;

    public List<RackAdapter.RackBoxModel.ProductData> racksDataResponse;
    public Context context;

    public NewSelectedOrderAdapter(Context context, List<RackAdapter.RackBoxModel.ProductData> racksDataResponse, PickupProcessMvpView pickupProcessMvpView, boolean isRackFlow, List<List<RackAdapter.RackBoxModel.ProductData>> listOfList, String fullfillmentId) {
        this.context = context;
        this.racksDataResponse = racksDataResponse;
        this.pickupProcessMvpView=pickupProcessMvpView;
        this.isRackFlow = isRackFlow;
        this.listOfList = listOfList;
        this.fullfillmentId = fullfillmentId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterSelectedPickupProcessProductsBinding pickupSummaryDetailsProductsBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_selected_pickup_process_products, parent, false);
        return new NewSelectedOrderAdapter.ViewHolder(pickupSummaryDetailsProductsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewSelectedOrderAdapter.ViewHolder holder, int position) {
        RackAdapter.RackBoxModel.ProductData dataResponse = racksDataResponse.get(position);
        pickupProcessMvpView.productsNextPosReturn(racksDataResponse);

        holder.pickupSummaryDetailsProductsBinding.productName.setText(dataResponse.getProductName());
        holder.pickupSummaryDetailsProductsBinding.rackId.setText(dataResponse.getRackId());
        holder.pickupSummaryDetailsProductsBinding.batchNo.setText(dataResponse.getBatchId());

        holder.pickupSummaryDetailsProductsBinding.statusUpdateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return racksDataResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public AdapterSelectedPickupProcessProductsBinding pickupSummaryDetailsProductsBinding;

        public ViewHolder(@NonNull AdapterSelectedPickupProcessProductsBinding pickupSummaryDetailsProductsBinding) {
            super(pickupSummaryDetailsProductsBinding.getRoot());
            this.pickupSummaryDetailsProductsBinding = pickupSummaryDetailsProductsBinding;
        }
    }
}
