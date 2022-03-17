package com.thresholdsoft.mpospicker.ui.orderdetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterOrderDetailsBinding;
import com.thresholdsoft.mpospicker.ui.openorders.adapter.FullfilmentAdapter;
import com.thresholdsoft.mpospicker.ui.orderdetails.OrderDetailsActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {
    private final Context context;
    List<RacksDataResponse.FullfillmentDetail.Product> products;

    public OrderDetailsAdapter(List<RacksDataResponse.FullfillmentDetail.Product> products, Context context) {
        this.context = context;
        this.products=products;

    }

    @NonNull
    @Override
    public OrderDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AdapterOrderDetailsBinding adapterOrderDetailsBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_order_details, parent, false);
       return new ViewHolder(adapterOrderDetailsBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.ViewHolder holder, int position) {


        RacksDataResponse.FullfillmentDetail.Product fullfillmentDetail = products.get(position);
        holder.adapterOrderDetailsBinding.productName.setText(fullfillmentDetail.getProductName());
        holder.adapterOrderDetailsBinding.quantity.setText(fullfillmentDetail.getAvailableQuantity());
        holder.adapterOrderDetailsBinding.rackId.setText(fullfillmentDetail.getRackId());


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterOrderDetailsBinding adapterOrderDetailsBinding;
        public ViewHolder(@NonNull AdapterOrderDetailsBinding adapterOrderDetailsBinding) {
            super(adapterOrderDetailsBinding.getRoot());
            this.adapterOrderDetailsBinding=adapterOrderDetailsBinding;
        }
    }
}
