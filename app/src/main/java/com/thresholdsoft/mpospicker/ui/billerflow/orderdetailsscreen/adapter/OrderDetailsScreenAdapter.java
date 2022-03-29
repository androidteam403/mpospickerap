package com.thresholdsoft.mpospicker.ui.billerflow.orderdetailsscreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterOrderDetailsScreenBinding;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public class OrderDetailsScreenAdapter extends RecyclerView.Adapter<OrderDetailsScreenAdapter.ViewHolder> {
    private Context context;
    List<RacksDataResponse.FullfillmentDetail.Product> products;

    public OrderDetailsScreenAdapter(Context context, List<RacksDataResponse.FullfillmentDetail.Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public OrderDetailsScreenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterOrderDetailsScreenBinding adapterBillerOrdersScreenBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_order_details_screen, parent, false);
        return new ViewHolder(adapterBillerOrdersScreenBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsScreenAdapter.ViewHolder holder, int position) {
        RacksDataResponse.FullfillmentDetail.Product fullfillmentDetail = products.get(position);
        holder.adapterOrderDetailsScreenBinding.productName.setText(fullfillmentDetail.getProductName());
        holder.adapterOrderDetailsScreenBinding.quantity.setText(fullfillmentDetail.getRequiredQuantity() + "/10");
        holder.adapterOrderDetailsScreenBinding.rackId.setText(fullfillmentDetail.getRackId());
    }

    @Override
    public int getItemCount() {
        return  products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterOrderDetailsScreenBinding adapterOrderDetailsScreenBinding;
        public ViewHolder(@NonNull  AdapterOrderDetailsScreenBinding adapterOrderDetailsScreenBinding) {
            super(adapterOrderDetailsScreenBinding.getRoot());
            this.adapterOrderDetailsScreenBinding=adapterOrderDetailsScreenBinding;
        }
    }
}

