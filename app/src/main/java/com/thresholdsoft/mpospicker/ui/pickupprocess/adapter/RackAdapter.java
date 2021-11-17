package com.thresholdsoft.mpospicker.ui.pickupprocess.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterRackBinding;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessMvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.ArrayList;
import java.util.List;

public class RackAdapter extends RecyclerView.Adapter<RackAdapter.ViewHolder> {
    private Context context;
    private List<RacksDataResponse.FullfillmentDetail.Product> rackDataFilteredList;
    private PickupProcessMvpView pickupProcessMvpView;
    RacksDataResponse dataResponse;
    List<List<RackBoxModel.ProductData>> listOfList;

    public RackAdapter(Context context, List<RacksDataResponse.FullfillmentDetail.Product> rackDataFilteredList, RacksDataResponse dataResponse, PickupProcessMvpView pickupProcessMvpView, List<List<RackBoxModel.ProductData>> listOfList) {
        this.context = context;
        this.rackDataFilteredList = rackDataFilteredList;
        this.pickupProcessMvpView = pickupProcessMvpView;
        this.dataResponse = dataResponse;
        this.listOfList = listOfList;
    }

    @NonNull
    @Override
    public RackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterRackBinding rackBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_rack, parent, false);
        return new RackAdapter.ViewHolder(rackBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull RackAdapter.ViewHolder holder, int position) {
        RacksDataResponse.FullfillmentDetail.Product rackModel = rackDataFilteredList.get(position);
        holder.rackBinding.rackId.setText(rackModel.getRackId());

        switch (rackModel.getExpandStatus()) {
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

        int flag = 0;
        List<RackBoxModel> boxStringList = new ArrayList<>();
        List<RackBoxModel.ProductData> productDataList = new ArrayList<>();
        for (int j = 0; j < dataResponse.getFullfillmentDetails().size(); j++) {
            for (int k = 0; k < dataResponse.getFullfillmentDetails().get(j).getProducts().size(); k++) {
                if (rackModel.getRackId().equalsIgnoreCase(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getRackId())) {
                    RackBoxModel rackBoxModel = new RackBoxModel();
                    rackBoxModel.setProductsCuont(flag + 1);
                    rackBoxModel.setRackBoxId(dataResponse.getFullfillmentDetails().get(j).getBoxId());
                    RackBoxModel.ProductData productData = new RackBoxModel.ProductData();
                    productData.setProductId(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getProductId());
                    productData.setProductName(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getProductName());
                    productData.setRackId(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getRackId());
                    productData.setBoxId(dataResponse.getFullfillmentDetails().get(j).getBoxId());
                    productData.setAvailableQuantity(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getAvailableQuantity());
                    productData.setRequiredQuantity(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getRequiredQuantity());
                    productData.setBatchId(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getBatchId());
                    if (listOfList != null && listOfList.size() > 0) {
                        for (int i = 0; i < listOfList.size(); i++) {
                            for (int l = 0; l < listOfList.get(i).size(); l++) {
                                if (listOfList.get(i).get(l).getProductId().equalsIgnoreCase(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getProductId())) {
                                    productData.setCapturedQuantity(listOfList.get(i).get(l).getCapturedQuantity());
                                    productData.setStatus(listOfList.get(i).get(l).getStatus());
                                }
                            }
                        }
                    } else {
                        if (pickupProcessMvpView.productList() != null && pickupProcessMvpView.productList().size() > 0) {
                            for (int i = 0; i < pickupProcessMvpView.productList().size(); i++) {
                                for (int l = 0; l < pickupProcessMvpView.productList().size(); l++) {
                                    if (pickupProcessMvpView.productList().get(i).get(l).getProductId().equalsIgnoreCase(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getProductId())) {
                                        productData.setCapturedQuantity(pickupProcessMvpView.productList().get(i).get(l).getCapturedQuantity());
                                        productData.setStatus(pickupProcessMvpView.productList().get(i).get(l).getStatus());
                                    }
                                }
                            }
                        } else {
                            productData.setCapturedQuantity(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getCapturedQuantity());
                            productData.setStatus(dataResponse.getFullfillmentDetails().get(j).getProducts().get(k).getStatus());
                        }
                    }
                    productDataList.add(productData);

                    boxStringList.add(rackBoxModel);
                }
            }
        }

        for (int i = 0; i < boxStringList.size(); i++) {
            for (int j = 0; j < boxStringList.size(); j++) {
                if (i != j && boxStringList.get(i).getRackBoxId().equals(boxStringList.get(j).getRackBoxId())) {
                    boxStringList.get(i).setProductsCuont(boxStringList.get(i).getProductsCuont() + boxStringList.get(j).getProductsCuont());
                    boxStringList.remove(j);
                }
            }
        }

        RackRowAdapter rackRowAdapter = new RackRowAdapter(context, boxStringList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        holder.rackBinding.rackRowRecycler.setLayoutManager(mLayoutManager);
        holder.rackBinding.rackRowRecycler.setAdapter(rackRowAdapter);


        ProductListAdapter productListAdapter = new ProductListAdapter(context, productDataList, pickupProcessMvpView, true, listOfList);
        new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        holder.rackBinding.productListRecycler.setLayoutManager(new LinearLayoutManager(context));
        holder.rackBinding.productListRecycler.setAdapter(productListAdapter);

        holder.rackBinding.rackChildLayout.setOnClickListener(v -> {
            if (rackDataFilteredList.get(position).getExpandStatus() == 1) {
                rackDataFilteredList.get(position).setExpandStatus(2);
                notifyDataSetChanged();
            } else if (rackDataFilteredList.get(position).getExpandStatus() == 2) {
                rackDataFilteredList.get(position).setExpandStatus(1);
                notifyDataSetChanged();
            } else if (rackDataFilteredList.get(position).getExpandStatus() == 0) {
                rackDataFilteredList.get(position).setExpandStatus(2);
                notifyDataSetChanged();
            }
        });
        if (position==rackDataFilteredList.size()-1){
            holder.rackBinding.gotoNextRack.setVisibility(View.GONE);
        }
        holder.rackBinding.gotoNextRack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rackDataFilteredList.get(position).getExpandStatus() == 2) {
                    rackDataFilteredList.get(position).setExpandStatus(1);
                    notifyItemChanged(position);
                }  if (rackDataFilteredList.get(position+1).getExpandStatus() == 0) {
                    rackDataFilteredList.get(position+1).setExpandStatus(2);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rackDataFilteredList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterRackBinding rackBinding;

        public ViewHolder(@NonNull AdapterRackBinding rackBinding) {
            super(rackBinding.getRoot());
            this.rackBinding = rackBinding;
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