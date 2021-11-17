package com.thresholdsoft.mpospicker.ui.pickupprocess.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.AdapterProductListBinding;
import com.thresholdsoft.mpospicker.databinding.DialogUpdateStatusBinding;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessMvpView;

import java.util.List;

public class FullfillmentProductListAdapter extends RecyclerView.Adapter<FullfillmentProductListAdapter.ViewHolder> {
    private Context context;
    private List<OrderAdapter.RackBoxModel.ProductData> productListModelList;
    private PickupProcessMvpView pickupProcessMvpView;
    private boolean isRackFlow;
    List<List<OrderAdapter.RackBoxModel.ProductData>> listOfList;

    public FullfillmentProductListAdapter(Context context, List<OrderAdapter.RackBoxModel.ProductData> productListModelList, PickupProcessMvpView pickupProcessMvpView, boolean isRackFlow, List<List<OrderAdapter.RackBoxModel.ProductData>> listOfList) {
        this.context = context;
        this.productListModelList = productListModelList;
        this.pickupProcessMvpView = pickupProcessMvpView;
        this.isRackFlow = isRackFlow;
        this.listOfList = listOfList;
    }

    @NonNull
    @Override
    public FullfillmentProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterProductListBinding productListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_product_list, parent, false);
        return new FullfillmentProductListAdapter.ViewHolder(productListBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull FullfillmentProductListAdapter.ViewHolder holder, int position) {
        OrderAdapter.RackBoxModel.ProductData productListModel = productListModelList.get(position);
        if (isRackFlow)
            holder.productListBinding.rackIdLayout.setVisibility(View.GONE);
        else
            holder.productListBinding.rackIdLayout.setVisibility(View.VISIBLE);

        if (productListModel.getStatus() != null && productListModel.getStatus().equalsIgnoreCase("Full")) {
            statusHandlings(holder.productListBinding);
            holder.productListBinding.fullStatusColor.setVisibility(View.VISIBLE);
        } else if (productListModel.getStatus() != null && productListModel.getStatus().equalsIgnoreCase("Partial")) {
            statusHandlings(holder.productListBinding);
            holder.productListBinding.partialStatusColor.setVisibility(View.VISIBLE);
        } else if (productListModel.getStatus() != null && productListModel.getStatus().equalsIgnoreCase("NotAvailable")) {
            statusHandlings(holder.productListBinding);
            holder.productListBinding.notAvailableStatusColor.setVisibility(View.VISIBLE);
        }

        if (productListModel.getCapturedQuantity() != null && !productListModel.getCapturedQuantity().isEmpty() && !productListModel.getCapturedQuantity().equalsIgnoreCase("")) {
            holder.productListBinding.capturesQty.setText(productListModel.getCapturedQuantity().toString() + "/");
        }
        if (productListModel.getBoxId() != null) {
            holder.productListBinding.rackRowId.setText(productListModel.getBoxId());
        } else {
            holder.productListBinding.rackBoxLay.setVisibility(View.GONE);
            holder.productListBinding.view.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 0.625f;
            params.setMarginStart(15);
            holder.productListBinding.proLay.setLayoutParams(params);
        }
        holder.productListBinding.productName.setText(productListModel.getProductName());
        holder.productListBinding.availableQty.setText(productListModel.getAvailableQuantity());
        holder.productListBinding.rackId.setText(productListModel.getRackId());
        holder.productListBinding.statusIcon.setOnClickListener(v -> {
            if (pickupProcessMvpView != null) {
                Dialog dialog = new Dialog(context, R.style.fadeinandoutcustomDialog);
                DialogUpdateStatusBinding updateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                        R.layout.dialog_update_status, null, false);
                fullQty = false;
                partialQty = false;
                notAvailableQty = false;
                dialog.setContentView(updateStatusBinding.getRoot());
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                radioButtonListener(updateStatusBinding, productListModel, position);
                updateStatusBinding.dismissDialog.setOnClickListener(v1 -> {
                    fullQty = false;
                    partialQty = false;
                    notAvailableQty = false;
                    dialog.dismiss();
                });
                updateStatusBinding.update.setOnClickListener(v12 -> {
                    if (fullQty) {
                        productListModelList.get(position).setCapturedQuantity(updateStatusBinding.qtyEditFull.getText().toString());
                        productListModel.setStatus("Full");
                        productListModel.setCapturedQuantity(updateStatusBinding.qtyEditFull.getText().toString());
                        holder.productListBinding.capturesQty.setText(updateStatusBinding.qtyEditFull.getText().toString() + "/");
                        statusHandlings(holder.productListBinding);
                        holder.productListBinding.fullStatusColor.setVisibility(View.VISIBLE);
                    } else if (partialQty) {
                        productListModelList.get(position).setCapturedQuantity(updateStatusBinding.qtyEditPartial.getText().toString());
                        productListModel.setStatus("Partial");
                        productListModel.setCapturedQuantity(updateStatusBinding.qtyEditPartial.getText().toString());
                        holder.productListBinding.capturesQty.setText(updateStatusBinding.qtyEditPartial.getText().toString() + "/");
                        statusHandlings(holder.productListBinding);
                        holder.productListBinding.partialStatusColor.setVisibility(View.VISIBLE);
                    } else if (notAvailableQty) {
                        statusHandlings(holder.productListBinding);
                        productListModel.setStatus("NotAvailable");
                        holder.productListBinding.notAvailableStatusColor.setVisibility(View.VISIBLE);
                    }
                    dialog.dismiss();
                    listOfList.add(productListModelList);
                    pickupProcessMvpView.onFullfillmentOrderStatusUpdate(listOfList);
                });
                dialog.show();
            }
        });
    }

    private void statusHandlings(AdapterProductListBinding productListBinding) {
        productListBinding.statusIcon.setVisibility(View.GONE);
        productListBinding.fullStatusColor.setVisibility(View.GONE);
        productListBinding.partialStatusColor.setVisibility(View.GONE);
        productListBinding.notAvailableStatusColor.setVisibility(View.GONE);
    }

    private boolean fullQty, partialQty, notAvailableQty;

    private void radioButtonListener(DialogUpdateStatusBinding dialogUpdateStatusBinding, OrderAdapter.RackBoxModel.ProductData productListQty, int position) {
        dialogUpdateStatusBinding.availableProFull.setText(productListQty.getAvailableQuantity());
        dialogUpdateStatusBinding.requiredProFull.setText(productListQty.getRequiredQuantity());

        dialogUpdateStatusBinding.availableProPartial.setText(productListQty.getAvailableQuantity());
        dialogUpdateStatusBinding.requiredProPartial.setText(productListQty.getRequiredQuantity());

        dialogUpdateStatusBinding.fullPickedRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogUpdateStatusBinding.fullPickedRadio.isChecked()) {
                    fullQty = true;
                    partialQty = false;
                    notAvailableQty = false;
                    dialogUpdateStatusBinding.fullPickedRadio.setChecked(true);
                    dialogUpdateStatusBinding.partiallyPickedRadio.setChecked(false);
                    dialogUpdateStatusBinding.notAvailableRadio.setChecked(false);
                    dialogUpdateStatusBinding.fullDetails.setVisibility(View.VISIBLE);
                    dialogUpdateStatusBinding.partialDetails.setVisibility(View.GONE);
                }
            }
        });
        dialogUpdateStatusBinding.partiallyPickedRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogUpdateStatusBinding.partiallyPickedRadio.isChecked()) {
                    fullQty = false;
                    partialQty = true;
                    notAvailableQty = false;
                    dialogUpdateStatusBinding.fullPickedRadio.setChecked(false);
                    dialogUpdateStatusBinding.partiallyPickedRadio.setChecked(true);
                    dialogUpdateStatusBinding.notAvailableRadio.setChecked(false);
                    dialogUpdateStatusBinding.fullDetails.setVisibility(View.GONE);
                    dialogUpdateStatusBinding.partialDetails.setVisibility(View.VISIBLE);
                }
            }
        });
        dialogUpdateStatusBinding.notAvailableRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogUpdateStatusBinding.notAvailableRadio.isChecked()) {
                    fullQty = false;
                    partialQty = false;
                    notAvailableQty = true;
                    dialogUpdateStatusBinding.fullPickedRadio.setChecked(false);
                    dialogUpdateStatusBinding.partiallyPickedRadio.setChecked(false);
                    dialogUpdateStatusBinding.notAvailableRadio.setChecked(true);
                    dialogUpdateStatusBinding.fullDetails.setVisibility(View.GONE);
                    dialogUpdateStatusBinding.partialDetails.setVisibility(View.GONE);
                }
            }
        });

        dialogUpdateStatusBinding.qtyDecreamentFull.setOnClickListener(v -> {
            if (Integer.parseInt(dialogUpdateStatusBinding.qtyEditFull.getText().toString().trim()) > 1) {
                dialogUpdateStatusBinding.qtyEditFull.setText(String.valueOf(Integer.parseInt(dialogUpdateStatusBinding.qtyEditFull.getText().toString().trim()) - 1));
            }
        });
        dialogUpdateStatusBinding.qtyIncreamentFull.setOnClickListener(v -> {
            dialogUpdateStatusBinding.qtyEditFull.setText(String.valueOf(Integer.parseInt(dialogUpdateStatusBinding.qtyEditFull.getText().toString().trim()) + 1));
        });

        dialogUpdateStatusBinding.qtyDecreamentPartial.setOnClickListener(v -> {
            if (Integer.parseInt(dialogUpdateStatusBinding.qtyEditPartial.getText().toString().trim()) > 1) {
                dialogUpdateStatusBinding.qtyEditPartial.setText(String.valueOf(Integer.parseInt(dialogUpdateStatusBinding.qtyEditPartial.getText().toString().trim()) - 1));
            }
        });
        dialogUpdateStatusBinding.qtyIncreamentPartial.setOnClickListener(v -> {
            dialogUpdateStatusBinding.qtyEditPartial.setText(String.valueOf(Integer.parseInt(dialogUpdateStatusBinding.qtyEditPartial.getText().toString().trim()) + 1));
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
}
