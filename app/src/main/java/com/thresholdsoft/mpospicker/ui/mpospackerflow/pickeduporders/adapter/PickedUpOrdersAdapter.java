package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterPickedUpordersBinding;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersMvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.ArrayList;
import java.util.List;

public class PickedUpOrdersAdapter extends RecyclerView.Adapter<PickedUpOrdersAdapter.ViewHolder> {
    private Context context;
    private List<RacksDataResponse.FullfillmentDetail> fullfillmentList;
    private PickedUpOrdersMvpView pickupProcessMvpView;
    List<List<RackAdapter.RackBoxModel.ProductData>> listOfList;
    private boolean firstAccessCheck;


    public PickedUpOrdersAdapter(Context context, List<RacksDataResponse.FullfillmentDetail> fullfillmentList, PickedUpOrdersMvpView pickupProcessMvpView, List<List<RackAdapter.RackBoxModel.ProductData>> fullfillmentListOfListFiltered, boolean acessCheck) {
        this.context = context;
        this.fullfillmentList = fullfillmentList;
        this.pickupProcessMvpView = pickupProcessMvpView;
        this.listOfList = fullfillmentListOfListFiltered;
        this.firstAccessCheck = acessCheck;
    }

    @NonNull
    @Override
    public PickedUpOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterPickedUpordersBinding orderBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_picked_uporders, parent, false);
        return new PickedUpOrdersAdapter.ViewHolder(orderBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull PickedUpOrdersAdapter.ViewHolder holder, int position) {
        RacksDataResponse.FullfillmentDetail fullFillModel = fullfillmentList.get(position);
        holder.orderBinding.fullfillmentID.setText(fullFillModel.getFullfillmentId());
        holder.orderBinding.totalItems.setText(String.valueOf(fullfillmentList.get(0).getProducts().size()));
        switch (fullFillModel.getExpandStatus()) {
            case 0:
                holder.orderBinding.orderChildLayout.setBackgroundColor(context.getResources().getColor(R.color.lite_grey));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.statusLayout.setVisibility(View.GONE);
                break;
            case 1:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_yellow_bg));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("In progress");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.in_progress));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_yellow_bg));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("In progress");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.in_progress));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                break;
            case 3:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Partial");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_partial));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                break;

            case 4:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Partial");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_partial));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                break;
            case 5:
                holder.orderBinding.orderChildLayout.setBackgroundColor(context.getResources().getColor(R.color.trans_red));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Not Available");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_not_available));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                break;
            case 6:
                holder.orderBinding.orderChildLayout.setBackgroundColor(context.getResources().getColor(R.color.trans_red));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Not Available");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_not_available));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                break;
            case 7:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_green));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Full");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                break;
            case 8:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_green));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Full");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                break;
            case 9:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_green));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Full");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                break;
            default:
        }

        List<RackAdapter.RackBoxModel.ProductData> productDataList = new ArrayList<>();
        for (int k = 0; k < fullFillModel.getProducts().size(); k++) {
            RackAdapter.RackBoxModel.ProductData productData = new RackAdapter.RackBoxModel.ProductData();
            productData.setProductId(fullFillModel.getProducts().get(k).getProductId());
            productData.setProductName(fullFillModel.getProducts().get(k).getProductName());
            productData.setRackId(fullFillModel.getProducts().get(k).getRackId());
            productData.setAvailableQuantity(fullFillModel.getProducts().get(k).getAvailableQuantity());
            productData.setRequiredQuantity(fullFillModel.getProducts().get(k).getRequiredQuantity());
            productData.setBatchId(fullFillModel.getProducts().get(k).getBatchId());
            productData.setPackerStatus(fullFillModel.getProducts().get(k).getPackerStatus());
            if (listOfList != null && listOfList.size() > 0) {
                for (int i = 0; i < listOfList.size(); i++) {
                    for (int l = 0; l < listOfList.get(i).size(); l++) {
                        if (listOfList.get(i).get(l).getProductId().equalsIgnoreCase(fullFillModel.getProducts().get(k).getProductId())) {
                            productData.setCapturedQuantity(listOfList.get(i).get(l).getCapturedQuantity());
                            productData.setStatus(listOfList.get(i).get(l).getStatus());
                            productData.setProductStatusFillingUpdate(listOfList.get(i).get(l).isProductStatusFillingUpdate());
                        }
                    }
                }
            } else {
                productData.setCapturedQuantity(fullFillModel.getProducts().get(k).getCapturedQuantity());
                productData.setStatus(fullFillModel.getProducts().get(k).getStatus());
                productData.setProductStatusFillingUpdate(fullFillModel.getProducts().get(k).isProductStatusFillingUpdate());
            }
            productDataList.add(productData);
        }

        if (!firstAccessCheck)
            firstTimeMultipleStatusCheck(productDataList, position, holder.orderBinding);
        holder.orderBinding.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pickupProcessMvpView != null) {
                    if (!holder.orderBinding.status.getText().toString().equalsIgnoreCase("Not Available"))
                        pickupProcessMvpView.onItemClick(position, holder.orderBinding.status.getText().toString(), productDataList, fullfillmentList,fullFillModel);
                }
            }
        });

    }


    private void firstTimeMultipleStatusCheck(List<RackAdapter.RackBoxModel.ProductData> productDataList, int position, AdapterPickedUpordersBinding rackBinding) {
        boolean full = false;
        boolean partial = false;
        boolean notAvailable = false;
        int notFlag = 0;
        int fullFalg = 0;

        if (listOfList != null && listOfList.size() > 0) {
            for (int i = 0; i < listOfList.size(); i++) {
                for (int k = 0; k < listOfList.get(i).size(); k++) {
                    for (int j = 0; j < productDataList.size(); j++) {
                        if (listOfList.get(i).get(k).getProductId().equalsIgnoreCase(productDataList.get(j).getProductId())) {
                            if (productDataList.get(j).getStatus() != null) {
                                if (productDataList.get(j).getStatus().equalsIgnoreCase(listOfList.get(i).get(k).getStatus()))
                                    productDataList.get(j).setStatus(listOfList.get(i).get(k).getStatus());
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < productDataList.size(); i++) {
            if (productDataList.get(i).getStatus() != null) {
                if (productDataList.get(i).getStatus().equalsIgnoreCase("Partial")) {
                    partial = true;
                    notAvailable = false;
                    full = false;
                } else if (productDataList.get(i).getStatus().equalsIgnoreCase("NotAvailable")) {
                    partial = true;
                    notFlag = notFlag + 1;
                    if (notFlag == productDataList.size()) {
                        notAvailable = true;
                        partial = false;
                        full = false;
                    }
                } else if (productDataList.get(i).getStatus().equalsIgnoreCase("Full")) {
                    partial = true;
                    fullFalg = fullFalg + 1;
                    if (fullFalg == productDataList.size()) {
                        full = true;
                        partial = false;
                        notAvailable = false;
                    }
                }
            }
        }

        if (partial) {
            fullfillmentList.get(position).setExpandStatus(3);
            rackBinding.start.setVisibility(View.GONE);
            rackBinding.status.setText("Partial");
            rackBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_partial));
            rackBinding.statusLayout.setVisibility(View.VISIBLE);
        } else if (notAvailable) {
            fullfillmentList.get(position).setExpandStatus(5);
            rackBinding.start.setVisibility(View.GONE);
            rackBinding.status.setText("Not Available");
            rackBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_not_available));
            rackBinding.statusLayout.setVisibility(View.VISIBLE);
        } else if (full) {
            fullfillmentList.get(position).setExpandStatus(7);
            rackBinding.start.setVisibility(View.GONE);
            rackBinding.status.setText("Full");
            rackBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
            rackBinding.statusLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return fullfillmentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterPickedUpordersBinding orderBinding;

        public ViewHolder(@NonNull AdapterPickedUpordersBinding orderBinding) {
            super(orderBinding.getRoot());
            this.orderBinding = orderBinding;
        }
    }

}