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
import com.thresholdsoft.mpospicker.databinding.AdapterRackBinding;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessMvpView;

import java.util.ArrayList;
import java.util.List;

public class RackAdapter extends RecyclerView.Adapter<RackAdapter.ViewHolder> {
    private Context context;
    private List<RackModel> rackModelList;
    private RackRowAdapter rackRowAdapter;
    private List<RackRowAdapter.RackRowModel> rackRowModelList;
    private ProductListAdapter productListAdapter;
    private List<ProductListAdapter.ProductListModel> productListModelList;
    private PickupProcessMvpView pickupProcessMvpView;

    public RackAdapter(Context context, List<RackModel> rackModelList, PickupProcessMvpView pickupProcessMvpView) {
        this.context = context;
        this.rackModelList = rackModelList;
        this.pickupProcessMvpView = pickupProcessMvpView;
    }

    @NonNull
    @Override
    public RackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterRackBinding rackBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_rack, parent, false);
        return new RackAdapter.ViewHolder(rackBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull RackAdapter.ViewHolder holder, int position) {
        RackModel rackModel = rackModelList.get(position);
        holder.rackBinding.rackId.setText(rackModel.getRackId());
        getRackRowModelList();
        getProductModelModelList();
        switch (rackModel.itemStatus) {
            case 0:
                holder.rackBinding.rackChildLayout.setBackgroundColor(context.getResources().getColor(R.color.lite_grey));
                holder.rackBinding.start.setVisibility(View.GONE);
                holder.rackBinding.statusLayout.setVisibility(View.GONE);
                holder.rackBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.rackBinding.rackChild2Layout.setBackground(null);
                break;
            case 1:
                holder.rackBinding.rackChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_yellow_bg));
                holder.rackBinding.start.setVisibility(View.VISIBLE);
                holder.rackBinding.statusLayout.setVisibility(View.GONE);
                holder.rackBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.rackBinding.rackChild2Layout.setBackground(null);
                break;
            case 2:
                holder.rackBinding.rackChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_yellow_bg));
                holder.rackBinding.start.setVisibility(View.GONE);
                holder.rackBinding.status.setText("In progress");
                holder.rackBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.in_progress));
                holder.rackBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.rackBinding.rackChild2Layout.setVisibility(View.VISIBLE);
                holder.rackBinding.rackChild2Layout.setBackground(context.getResources().getDrawable(R.drawable.yellow_stroke_bg));

                break;
            case 3:
                holder.rackBinding.rackChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
                holder.rackBinding.start.setVisibility(View.GONE);
                holder.rackBinding.status.setText("Partial");
                holder.rackBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_partial));
                holder.rackBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.rackBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.rackBinding.rackChild2Layout.setBackground(null);
                break;
            case 4:
                holder.rackBinding.rackChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
                holder.rackBinding.start.setVisibility(View.GONE);
                holder.rackBinding.status.setText("Not Available");
                holder.rackBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_not_available));
                holder.rackBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.rackBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.rackBinding.rackChild2Layout.setBackground(null);
                break;
            case 5:
                holder.rackBinding.rackChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
                holder.rackBinding.start.setVisibility(View.GONE);
                holder.rackBinding.status.setText("Full");
                holder.rackBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
                holder.rackBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.rackBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.rackBinding.rackChild2Layout.setBackground(null);
                break;
            default:
        }
        rackRowAdapter = new RackRowAdapter(context, rackRowModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        holder.rackBinding.rackRowRecycler.setLayoutManager(mLayoutManager);
        holder.rackBinding.rackRowRecycler.setAdapter(rackRowAdapter);

        productListAdapter = new ProductListAdapter(context, productListModelList, pickupProcessMvpView, true);
        new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        holder.rackBinding.productListRecycler.setLayoutManager(new LinearLayoutManager(context));
        holder.rackBinding.productListRecycler.setAdapter(productListAdapter);

        holder.rackBinding.rackChildLayout.setOnClickListener(v -> {
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
        AdapterRackBinding rackBinding;

        public ViewHolder(@NonNull AdapterRackBinding rackBinding) {
            super(rackBinding.getRoot());
            this.rackBinding = rackBinding;
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

    private void getRackRowModelList() {
        rackRowModelList = new ArrayList<>();
        RackRowAdapter.RackRowModel rackRowModel = new RackRowAdapter.RackRowModel();
        rackRowModel.setRackRowId("B1");
        rackRowModel.setRackRowNo("1");
        rackRowModelList.add(rackRowModel);

        rackRowModel = new RackRowAdapter.RackRowModel();
        rackRowModel.setRackRowId("B2");
        rackRowModel.setRackRowNo("2");
        rackRowModelList.add(rackRowModel);

        rackRowModel = new RackRowAdapter.RackRowModel();
        rackRowModel.setRackRowId("B3");
        rackRowModel.setRackRowNo("3");
        rackRowModelList.add(rackRowModel);

        rackRowModel = new RackRowAdapter.RackRowModel();
        rackRowModel.setRackRowId("B4");
        rackRowModel.setRackRowNo("4");
        rackRowModelList.add(rackRowModel);
    }

    public static class RackModel {
        private String rackId;
        private int itemStatus;
        private String fullfillmentID;
        private String totalItems;
        private String boxID;

        public String getRackId() {
            return rackId;
        }

        public void setRackId(String rackId) {
            this.rackId = rackId;
        }

        public int getItemStatus() {
            return itemStatus;
        }

        public void setItemStatus(int itemStatus) {
            this.itemStatus = itemStatus;
        }

        public String getFullfillmentID() {
            return fullfillmentID;
        }

        public void setFullfillmentID(String fullfillmentID) {
            this.fullfillmentID = fullfillmentID;
        }

        public String getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(String totalItems) {
            this.totalItems = totalItems;
        }

        public String getBoxID() {
            return boxID;
        }

        public void setBoxID(String boxID) {
            this.boxID = boxID;
        }
    }
}