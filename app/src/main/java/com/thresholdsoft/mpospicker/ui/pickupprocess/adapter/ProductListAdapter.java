package com.thresholdsoft.mpospicker.ui.pickupprocess.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterProductListBinding;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessMvpView;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private Context context;
    private List<ProductListModel> productListModelList;
    private PickupProcessMvpView pickupProcessMvpView;
    private boolean isRackFlow;

    public ProductListAdapter(Context context, List<ProductListModel> productListModelList, PickupProcessMvpView pickupProcessMvpView, boolean isRackFlow) {
        this.context = context;
        this.productListModelList = productListModelList;
        this.pickupProcessMvpView = pickupProcessMvpView;
        this.isRackFlow = isRackFlow;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterProductListBinding productListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_product_list, parent, false);
        return new ProductListAdapter.ViewHolder(productListBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        ProductListModel productListModel = productListModelList.get(position);
        if (isRackFlow)
            holder.productListBinding.rackIdLayout.setVisibility(View.GONE);
        else
            holder.productListBinding.rackIdLayout.setVisibility(View.VISIBLE);
        holder.productListBinding.rackRowId.setText(productListModel.getRackId());
        holder.productListBinding.productName.setText(productListModel.getProductName());
        holder.productListBinding.qty.setText(productListModel.getQty());
        holder.productListBinding.rackId.setText(productListModel.getRack());
        holder.productListBinding.statusIcon.setOnClickListener(v -> {
            if (pickupProcessMvpView != null) {
                pickupProcessMvpView.onStatusClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productListModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterProductListBinding productListBinding;

        public ViewHolder(@NonNull AdapterProductListBinding productListBinding) {
            super(productListBinding.getRoot());
            this.productListBinding = productListBinding;
        }
    }

    public static class ProductListModel {
        private String rackId;
        private String productName;
        private String qty;
        private String productStatus;
        private String rack;

        public String getRackId() {
            return rackId;
        }

        public void setRackId(String rackId) {
            this.rackId = rackId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getProductStatus() {
            return productStatus;
        }

        public void setProductStatus(String productStatus) {
            this.productStatus = productStatus;
        }

        public String getRack() {
            return rack;
        }

        public void setRack(String rack) {
            this.rack = rack;
        }
    }
}
