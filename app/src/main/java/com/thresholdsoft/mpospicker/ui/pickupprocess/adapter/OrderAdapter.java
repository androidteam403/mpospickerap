package com.thresholdsoft.mpospicker.ui.pickupprocess.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterOrderBinding;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessMvpView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private List<RackAdapter.RackModel> rackModelList;
    private RackRowAdapter rackRowAdapter;
    private List<RackRowAdapter.RackRowModel> rackRowModelList;
    private ProductListAdapter productListAdapter;
    private List<ProductListAdapter.ProductListModel> productListModelList;
    private PickupProcessMvpView pickupProcessMvpView;

    public OrderAdapter(Context context, List<RackAdapter.RackModel> rackModelList, PickupProcessMvpView pickupProcessMvpView) {
        this.context = context;
        this.rackModelList = rackModelList;
        this.pickupProcessMvpView = pickupProcessMvpView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterOrderBinding orderBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_order, parent, false);
        return new ViewHolder(orderBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RackAdapter.RackModel rackModel = rackModelList.get(position);
        getProductModelModelList();
        holder.orderBinding.fullfillmentID.setText(rackModel.getFullfillmentID());
        holder.orderBinding.totalItems.setText(rackModel.getTotalItems());
        holder.orderBinding.boxId.setText(rackModel.getBoxID());
        switch (rackModel.getItemStatus()) {
            case 0:
                holder.orderBinding.orderChildLayout.setBackgroundColor(context.getResources().getColor(R.color.lite_grey));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.statusLayout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            case 1:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_yellow_bg));
                holder.orderBinding.start.setVisibility(View.VISIBLE);
                holder.orderBinding.statusLayout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            case 2:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_yellow_bg));
                holder.orderBinding.start.setVisibility(View.VISIBLE);
                holder.orderBinding.start.setText("collecting");
                holder.orderBinding.statusLayout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setBackground(context.getResources().getDrawable(R.drawable.yellow_stroke_bg));
                break;
            case 3:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Partial");
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            case 4:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Not Available");
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            case 5:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Full");
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            default:
        }


        productListAdapter = new ProductListAdapter(context, productListModelList, pickupProcessMvpView, false);
        new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        holder.orderBinding.productListRecycler.setLayoutManager(new LinearLayoutManager(context));
        holder.orderBinding.productListRecycler.setAdapter(productListAdapter);

        holder.orderBinding.orderChildLayout.setOnClickListener(v -> {
            if (rackModel.getItemStatus() == 1) {
                rackModel.setItemStatus(2);
                notifyDataSetChanged();
            } else if (rackModel.getItemStatus() == 2) {
                rackModel.setItemStatus(1);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rackModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterOrderBinding orderBinding;

        public ViewHolder(@NonNull AdapterOrderBinding orderBinding) {
            super(orderBinding.getRoot());
            this.orderBinding = orderBinding;
        }
    }

    private void getProductModelModelList() {
        productListModelList = new ArrayList<>();
        ProductListAdapter.ProductListModel productListModel = new ProductListAdapter.ProductListModel();
        productListModel.setRackId("B1");
        productListModel.setProductName("Ascoril D Syrup Sugar Free");
        productListModel.setQty("1/1");
        productListModel.setRack("G098-98-815");
        productListModelList.add(productListModel);

        productListModel = new ProductListAdapter.ProductListModel();
        productListModel.setRackId("B2");
        productListModel.setProductName("Allegra 180mg Tablet");
        productListModel.setQty("0/5");
        productListModel.setRack("G098-98-815");
        productListModelList.add(productListModel);

        productListModel = new ProductListAdapter.ProductListModel();
        productListModel.setRackId("B3");
        productListModel.setProductName("Amoxyclav 625 Tablet");
        productListModel.setQty("0/3");
        productListModel.setRack("G098-98-815");
        productListModelList.add(productListModel);

        productListModel = new ProductListAdapter.ProductListModel();
        productListModel.setRackId("B4");
        productListModel.setProductName("Augmentin 625 Duo Tablet");
        productListModel.setQty("0/10");
        productListModel.setRack("G098-98-815");
        productListModelList.add(productListModel);

        productListModel = new ProductListAdapter.ProductListModel();
        productListModel.setRackId("B4");
        productListModel.setProductName("Azithral 500 Tablet");
        productListModel.setQty("0/10");
        productListModel.setRack("G098-98-815");
        productListModelList.add(productListModel);
    }
}
