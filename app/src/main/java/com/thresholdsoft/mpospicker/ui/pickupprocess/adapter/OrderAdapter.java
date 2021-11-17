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
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private RacksDataResponse fullfillmentList;
    private FullfillmentProductListAdapter productListAdapter;
    private PickupProcessMvpView pickupProcessMvpView;
    List<List<RackBoxModel.ProductData>> listOfList;

    public OrderAdapter(Context context, RacksDataResponse fullfillmentList, PickupProcessMvpView pickupProcessMvpView, List<List<RackBoxModel.ProductData>> fullfillmentListOfListFiltered) {
        this.context = context;
        this.fullfillmentList = fullfillmentList;
        this.pickupProcessMvpView = pickupProcessMvpView;
        this.listOfList = fullfillmentListOfListFiltered;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterOrderBinding orderBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_order, parent, false);
        return new ViewHolder(orderBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RacksDataResponse.FullfillmentDetail fullFillModel = fullfillmentList.getFullfillmentDetails().get(position);
        holder.orderBinding.fullfillmentID.setText(fullFillModel.getFullfillmentId());
        holder.orderBinding.totalItems.setText(String.valueOf(fullfillmentList.getFullfillmentDetails().get(0).getProducts().size()));
        holder.orderBinding.boxId.setText(fullFillModel.getBoxId());
        switch (fullFillModel.getExpandStatus()) {
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

        List<RackBoxModel.ProductData> productDataList = new ArrayList<>();


        for (int k = 0; k < fullFillModel.getProducts().size(); k++) {
            RackBoxModel.ProductData productData = new RackBoxModel.ProductData();
            productData.setProductId(fullFillModel.getProducts().get(k).getProductId());
            productData.setProductName(fullFillModel.getProducts().get(k).getProductName());
            productData.setRackId(fullFillModel.getProducts().get(k).getRackId());
            productData.setAvailableQuantity(fullFillModel.getProducts().get(k).getAvailableQuantity());
            productData.setRequiredQuantity(fullFillModel.getProducts().get(k).getRequiredQuantity());
            productData.setBatchId(fullFillModel.getProducts().get(k).getBatchId());
            if (listOfList != null && listOfList.size() > 0) {
                for (int i = 0; i < listOfList.size(); i++) {
                    for (int l = 0; l < listOfList.get(i).size(); l++) {
                        if (listOfList.get(i).get(l).getProductId().equalsIgnoreCase(fullFillModel.getProducts().get(k).getProductId())) {
                            productData.setCapturedQuantity(listOfList.get(i).get(l).getCapturedQuantity());
                            productData.setStatus(listOfList.get(i).get(l).getStatus());
                        }
                    }
                }
            } else {
                if (pickupProcessMvpView.fullfilListOfList() != null && pickupProcessMvpView.fullfilListOfList().size() > 0) {
                    for (int i = 0; i < pickupProcessMvpView.fullfilListOfList().size(); i++) {
                        for (int l = 0; l < pickupProcessMvpView.fullfilListOfList().size(); l++) {
                            if (pickupProcessMvpView.fullfilListOfList().get(i).get(l).getProductId().equalsIgnoreCase(fullFillModel.getProducts().get(k).getProductId())) {
                                productData.setCapturedQuantity(pickupProcessMvpView.fullfilListOfList().get(i).get(l).getCapturedQuantity());
                                productData.setStatus(pickupProcessMvpView.fullfilListOfList().get(i).get(l).getStatus());
                            }
                        }
                    }
                } else {
                    productData.setCapturedQuantity(fullFillModel.getProducts().get(k).getCapturedQuantity());
                    productData.setStatus(fullFillModel.getProducts().get(k).getStatus());
                }
            }
            productDataList.add(productData);
        }

//        productListAdapter = new ProductListAdapter(context, productListModelList, pickupProcessMvpView, false);
        productListAdapter = new FullfillmentProductListAdapter(context, productDataList, pickupProcessMvpView, false, listOfList);
        new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        holder.orderBinding.productListRecycler.setLayoutManager(new LinearLayoutManager(context));
        holder.orderBinding.productListRecycler.setAdapter(productListAdapter);

        holder.orderBinding.orderChildLayout.setOnClickListener(v -> {
            if (fullfillmentList.getFullfillmentDetails().get(position).getExpandStatus() == 1) {
                fullfillmentList.getFullfillmentDetails().get(position).setExpandStatus(2);
                notifyDataSetChanged();
            } else if (fullfillmentList.getFullfillmentDetails().get(position).getExpandStatus() == 2) {
                fullfillmentList.getFullfillmentDetails().get(position).setExpandStatus(1);
                notifyDataSetChanged();
            } else if (fullfillmentList.getFullfillmentDetails().get(position).getExpandStatus() == 0) {
                fullfillmentList.getFullfillmentDetails().get(position).setExpandStatus(2);
                notifyDataSetChanged();
            }
        });

        if (position == fullfillmentList.getFullfillmentDetails().size() - 1) {
            holder.orderBinding.gotoNextRack.setVisibility(View.GONE);
        }

        holder.orderBinding.gotoNextRack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullfillmentList.getFullfillmentDetails().get(position).getExpandStatus() == 2) {
                    fullfillmentList.getFullfillmentDetails().get(position).setExpandStatus(1);
                    notifyItemChanged(position);
                }
                if (fullfillmentList.getFullfillmentDetails().get(position + 1).getExpandStatus() == 0) {
                    fullfillmentList.getFullfillmentDetails().get(position + 1).setExpandStatus(2);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fullfillmentList.getFullfillmentDetails().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterOrderBinding orderBinding;

        public ViewHolder(@NonNull AdapterOrderBinding orderBinding) {
            super(orderBinding.getRoot());
            this.orderBinding = orderBinding;
        }
    }

    public static class RackBoxModel {
        private String rackBoxId;
        private int productsCuont;

        public int getProductsCuont() {
            return productsCuont;
        }

        public void setProductsCuont(int productsCuont) {
            this.productsCuont = productsCuont;
        }

        public String getRackBoxId() {
            return rackBoxId;
        }

        public void setRackBoxId(String rackBoxId) {
            this.rackBoxId = rackBoxId;
        }

        public static class ProductData {

            private String productId;
            private String productName;
            private String rackId;
            private String boxId;
            private String availableQuantity;
            private String requiredQuantity;
            private String capturedQuantity;
            private String batchId;
            private String status;

            public String getBoxId() {
                return boxId;
            }

            public void setBoxId(String boxId) {
                this.boxId = boxId;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getRackId() {
                return rackId;
            }

            public void setRackId(String rackId) {
                this.rackId = rackId;
            }

            public String getAvailableQuantity() {
                return availableQuantity;
            }

            public void setAvailableQuantity(String availableQuantity) {
                this.availableQuantity = availableQuantity;
            }

            public String getRequiredQuantity() {
                return requiredQuantity;
            }

            public void setRequiredQuantity(String requiredQuantity) {
                this.requiredQuantity = requiredQuantity;
            }

            public String getCapturedQuantity() {
                return capturedQuantity;
            }

            public void setCapturedQuantity(String capturedQuantity) {
                this.capturedQuantity = capturedQuantity;
            }

            public String getBatchId() {
                return batchId;
            }

            public void setBatchId(String batchId) {
                this.batchId = batchId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
