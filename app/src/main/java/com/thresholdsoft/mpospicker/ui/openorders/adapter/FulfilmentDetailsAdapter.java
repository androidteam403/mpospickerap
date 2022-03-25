package com.thresholdsoft.mpospicker.ui.openorders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterSelectedPickupProcessProductsBinding;
import com.thresholdsoft.mpospicker.databinding.DialogCustomUpdateStatusBinding;
import com.thresholdsoft.mpospicker.databinding.DialogUpdateStatusBinding;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.SelectedOrderPickupProcessMvpView;

import java.util.List;

public class FulfilmentDetailsAdapter extends RecyclerView.Adapter<FulfilmentDetailsAdapter.ViewHolder> {
    public List<RacksDataResponse.FullfillmentDetail.Product> products;
    public  Context context;
    private OpenOrdersMvpView mvpView;
    public DialogUpdateStatusBinding updateStatusBinding;
    private int fullFillmentPos;

    public FulfilmentDetailsAdapter(Context context, List<RacksDataResponse.FullfillmentDetail.Product> products,OpenOrdersMvpView mvpView, int fullFillmentPos) {
        this.products = products;
        this.context = context;
        this.mvpView=mvpView;
        this.fullFillmentPos=fullFillmentPos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterSelectedPickupProcessProductsBinding pickupSummaryDetailsProductsBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_selected_pickup_process_products, parent, false);
        return new FulfilmentDetailsAdapter.ViewHolder(pickupSummaryDetailsProductsBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RacksDataResponse.FullfillmentDetail.Product dataResponse = products.get(position);

        holder.pickupSummaryDetailsProductsBinding.statusUpdateIcon.setOnClickListener(view -> {
                mvpView.onClickStausIcon(fullFillmentPos, position);
        });



                if(dataResponse.getItemStatus().equals("FULL VERIFIED")){
                    holder.pickupSummaryDetailsProductsBinding.statusUpdateIcon.setVisibility(View.GONE);
                    holder.pickupSummaryDetailsProductsBinding.statusUpdateIconNotavialbale.setVisibility(View.GONE);
                    holder.pickupSummaryDetailsProductsBinding.statusUpdateIconFullverified.setVisibility(View.VISIBLE);
                }
                else if(dataResponse.getItemStatus().equals("PARTIAL VERIFIED")){
                    holder.pickupSummaryDetailsProductsBinding.statusUpdateIcon.setVisibility(View.GONE);
                    holder.pickupSummaryDetailsProductsBinding.statusUpdateIconFullverified.setVisibility(View.GONE);
                    holder.pickupSummaryDetailsProductsBinding.statusUpdateIconNotavialbale.setVisibility(View.VISIBLE);
                }
                else if(dataResponse.getItemStatus().equals("NOT AVAILABLE")){
                    holder.pickupSummaryDetailsProductsBinding.statusUpdateIcon.setVisibility(View.VISIBLE);
                    holder.pickupSummaryDetailsProductsBinding.statusUpdateIconFullverified.setVisibility(View.GONE);
                    holder.pickupSummaryDetailsProductsBinding.statusUpdateIconNotavialbale.setVisibility(View.GONE);



                }






        holder.pickupSummaryDetailsProductsBinding.productName.setText(dataResponse.getProductName());
        holder.pickupSummaryDetailsProductsBinding.rackId.setText(dataResponse.getRackId());
        holder.pickupSummaryDetailsProductsBinding.batchNo.setText(dataResponse.getBatchId());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public AdapterSelectedPickupProcessProductsBinding pickupSummaryDetailsProductsBinding;
public DialogUpdateStatusBinding updateStatusBinding;
        public ViewHolder(@NonNull AdapterSelectedPickupProcessProductsBinding pickupSummaryDetailsProductsBinding) {
            super(pickupSummaryDetailsProductsBinding.getRoot());
            this.pickupSummaryDetailsProductsBinding = pickupSummaryDetailsProductsBinding;
        }
    }
}
