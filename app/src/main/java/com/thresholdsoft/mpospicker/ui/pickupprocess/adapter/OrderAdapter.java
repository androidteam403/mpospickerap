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
    private List<RacksDataResponse.FullfillmentDetail> fullfillmentList;
    private FullfillmentProductListAdapter productListAdapter;
    private PickupProcessMvpView pickupProcessMvpView;
    List<List<RackAdapter.RackBoxModel.ProductData>> listOfList;
    private boolean firstAccessCheck;

    public OrderAdapter(Context context, List<RacksDataResponse.FullfillmentDetail> fullfillmentList, PickupProcessMvpView pickupProcessMvpView, List<List<RackAdapter.RackBoxModel.ProductData>> fullfillmentListOfListFiltered, boolean acessCheck) {
        this.context = context;
        this.fullfillmentList = fullfillmentList;
        this.pickupProcessMvpView = pickupProcessMvpView;
        this.listOfList = fullfillmentListOfListFiltered;
        this.firstAccessCheck = acessCheck;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterOrderBinding orderBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_order, parent, false);
        return new ViewHolder(orderBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RacksDataResponse.FullfillmentDetail fullFillModel = fullfillmentList.get(position);
        holder.orderBinding.fullfillmentID.setText(fullFillModel.getFullfillmentId());
        holder.orderBinding.totalItems.setText(String.valueOf(fullfillmentList.get(0).getProducts().size()));
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
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("In progress");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.in_progress));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setBackground(context.getResources().getDrawable(R.drawable.yellow_stroke_bg));
                break;
            case 2:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_yellow_bg));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("In progress");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.in_progress));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setBackground(context.getResources().getDrawable(R.drawable.yellow_stroke_bg));
                break;
            case 3:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Partial");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_partial));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;

            case 4:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Partial");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_partial));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            case 5:
                holder.orderBinding.orderChildLayout.setBackgroundColor(context.getResources().getColor(R.color.trans_red));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Not Available");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_not_available));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            case 6:
                holder.orderBinding.orderChildLayout.setBackgroundColor(context.getResources().getColor(R.color.trans_red));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Not Available");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_not_available));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            case 7:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_green));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Full");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            case 8:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_green));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Full");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            case 9:
                holder.orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_green));
                holder.orderBinding.start.setVisibility(View.GONE);
                holder.orderBinding.status.setText("Full");
                holder.orderBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
                holder.orderBinding.statusLayout.setVisibility(View.VISIBLE);
                holder.orderBinding.rackChild2Layout.setVisibility(View.GONE);
                holder.orderBinding.rackChild2Layout.setBackground(null);
                break;
            default:
        }

        List<RackAdapter.RackBoxModel.ProductData> productDataList = new ArrayList<>();


        for (int k = 0; k < fullFillModel.getProducts().size(); k++) {
            RackAdapter.RackBoxModel.ProductData productData = new RackAdapter.RackBoxModel.ProductData();
            productData.setProductId(fullFillModel.getProducts().get(k).getProductId());
            productData.setProductName(fullFillModel.getProducts().get(k).getProductName());
            productData.setRackId(fullFillModel.getProducts().get(k).getRackId());
            productData.setBoxId(fullFillModel.getBoxId());
            productData.setAvailableQuantity(fullFillModel.getProducts().get(k).getAvailableQuantity());
            productData.setRequiredQuantity(fullFillModel.getProducts().get(k).getRequiredQuantity());
            productData.setBatchId(fullFillModel.getProducts().get(k).getBatchId());
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
                if (pickupProcessMvpView.fullfilListOfList() != null && pickupProcessMvpView.fullfilListOfList().size() > 0) {
                    for (int i = 0; i < pickupProcessMvpView.fullfilListOfList().size(); i++) {
                        for (int l = 0; l < pickupProcessMvpView.fullfilListOfList().size(); l++) {
                            if (pickupProcessMvpView.fullfilListOfList().get(i).get(l).getProductId().equalsIgnoreCase(fullFillModel.getProducts().get(k).getProductId())) {
                                productData.setCapturedQuantity(pickupProcessMvpView.fullfilListOfList().get(i).get(l).getCapturedQuantity());
                                productData.setStatus(pickupProcessMvpView.fullfilListOfList().get(i).get(l).getStatus());
                                productData.setProductStatusFillingUpdate(pickupProcessMvpView.fullfilListOfList().get(i).get(l).isProductStatusFillingUpdate());
                            }
                        }
                    }
                } else {
                    productData.setCapturedQuantity(fullFillModel.getProducts().get(k).getCapturedQuantity());
                    productData.setStatus(fullFillModel.getProducts().get(k).getStatus());
                    productData.setProductStatusFillingUpdate(fullFillModel.getProducts().get(k).isProductStatusFillingUpdate());
                }
            }
            productDataList.add(productData);
        }

//        completedViewCheck(productDataList,position,holder.orderBinding);
//        multipleStatusCheck(productDataList,position);
        if (!firstAccessCheck)
            firstTimeMultipleStatusCheck(productDataList, position, holder.orderBinding);

        productListAdapter = new FullfillmentProductListAdapter(context, productDataList, pickupProcessMvpView, false, listOfList,fullFillModel.getFullfillmentId());
        new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        holder.orderBinding.productListRecycler.setLayoutManager(new LinearLayoutManager(context));
        holder.orderBinding.productListRecycler.setAdapter(productListAdapter);

        holder.orderBinding.orderChildLayout.setOnClickListener(v -> {

            firstAccessCheck = true;

//            completedViewCheck(productDataList,position,holder.orderBinding);
            if (fullfillmentList.get(position).getExpandStatus() == 0) {
                fullfillmentList.get(position).setExpandStatus(1);
                notifyDataSetChanged();
            } else if (fullfillmentList.get(position).getExpandStatus() == 2) {
                fullfillmentList.get(position).setExpandStatus(1);
                notifyDataSetChanged();
            } else if (fullfillmentList.get(position).getExpandStatus() == 1) {
                multipleStatusCheck(productDataList, position);
            } else if (fullfillmentList.get(position).getExpandStatus() == 3) {
                fullfillmentList.get(position).setExpandStatus(4);
                notifyDataSetChanged();
            } else if (fullfillmentList.get(position).getExpandStatus() == 4) {
                multipleStatusCheck(productDataList, position);
            } else if (fullfillmentList.get(position).getExpandStatus() == 5) {
                fullfillmentList.get(position).setExpandStatus(6);
                notifyDataSetChanged();
            } else if (fullfillmentList.get(position).getExpandStatus() == 6) {
                multipleStatusCheck(productDataList, position);
            } else if (fullfillmentList.get(position).getExpandStatus() == 7) {
                fullfillmentList.get(position).setExpandStatus(8);
                notifyDataSetChanged();
            } else if (fullfillmentList.get(position).getExpandStatus() == 8) {
//                rackDataFilteredList.get(position).setExpandStatus(9);
//                notifyDataSetChanged();
                multipleStatusCheck(productDataList, position);
            } else if (fullfillmentList.get(position).getExpandStatus() == 9) {
//                multipleStatusCheck(productDataList, position);
            }
        });


        holder.orderBinding.gotoNextRack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullfillmentList.get(position).getExpandStatus() == 0) {
                    fullfillmentList.get(position).setExpandStatus(1);
                    goToNextCheck(position + 1, productDataList);
                    if (position + 1 >= fullfillmentList.size())
                        goToNextCheck(0, productDataList);
                    else
                        goToNextCheck(position + 1, productDataList);
                    notifyDataSetChanged();
                } else if (fullfillmentList.get(position).getExpandStatus() == 2) {
                    fullfillmentList.get(position).setExpandStatus(1);
                    if (position + 1 >= fullfillmentList.size())
                        goToNextCheck(0, productDataList);
                    else
                        goToNextCheck(position + 1, productDataList);
                    notifyDataSetChanged();
                } else if (fullfillmentList.get(position).getExpandStatus() == 1) {
                    multipleStatusCheck(productDataList, position);
                    if (position + 1 >= fullfillmentList.size())
                        goToNextCheck(0, productDataList);
                    else
                        goToNextCheck(position + 1, productDataList);
                } else if (fullfillmentList.get(position).getExpandStatus() == 3) {
                    fullfillmentList.get(position).setExpandStatus(4);
                    if (position + 1 >= fullfillmentList.size())
                        goToNextCheck(0, productDataList);
                    else
                        goToNextCheck(position + 1, productDataList);
                    notifyDataSetChanged();
                } else if (fullfillmentList.get(position).getExpandStatus() == 4) {
                    multipleStatusCheck(productDataList, position);
                    if (position + 1 >= fullfillmentList.size())
                        goToNextCheck(0, productDataList);
                    else
                        goToNextCheck(position + 1, productDataList);
                } else if (fullfillmentList.get(position).getExpandStatus() == 5) {
                    fullfillmentList.get(position).setExpandStatus(6);
                    if (position + 1 >= fullfillmentList.size())
                        goToNextCheck(0, productDataList);
                    else
                        goToNextCheck(position + 1, productDataList);
                    notifyDataSetChanged();
                } else if (fullfillmentList.get(position).getExpandStatus() == 6) {
                    multipleStatusCheck(productDataList, position);
                    if (position + 1 >= fullfillmentList.size())
                        goToNextCheck(0, productDataList);
                    else
                        goToNextCheck(position + 1, productDataList);
                } else if (fullfillmentList.get(position).getExpandStatus() == 7) {
                    fullfillmentList.get(position).setExpandStatus(8);
                    if (position + 1 >= fullfillmentList.size())
                        goToNextCheck(0, productDataList);
                    else
                        goToNextCheck(position + 1, productDataList);
                    notifyDataSetChanged();
                } else if (fullfillmentList.get(position).getExpandStatus() == 8) {
                    multipleStatusCheck(productDataList, position);
                    if (position + 1 >= fullfillmentList.size())
                        goToNextCheck(0, productDataList);
                    else
                        goToNextCheck(position + 1, productDataList);
                }
            }
        });
    }

    private void goToNextCheck(int position, List<RackAdapter.RackBoxModel.ProductData> productDataList) {
        if (fullfillmentList.get(position).getExpandStatus() == 0) {
            fullfillmentList.get(position).setExpandStatus(1);
            notifyDataSetChanged();
        } else if (fullfillmentList.get(position).getExpandStatus() == 2) {
            fullfillmentList.get(position).setExpandStatus(1);
            notifyDataSetChanged();
        } else if (fullfillmentList.get(position).getExpandStatus() == 1) {
            multipleStatusCheckDummy(position);
        } else if (fullfillmentList.get(position).getExpandStatus() == 3) {
            fullfillmentList.get(position).setExpandStatus(4);
            notifyDataSetChanged();
        } else if (fullfillmentList.get(position).getExpandStatus() == 4) {
            multipleStatusCheckDummy(position);
        } else if (fullfillmentList.get(position).getExpandStatus() == 5) {
            fullfillmentList.get(position).setExpandStatus(6);
            notifyDataSetChanged();
        } else if (fullfillmentList.get(position).getExpandStatus() == 6) {
            multipleStatusCheckDummy(position);
        } else if (fullfillmentList.get(position).getExpandStatus() == 7) {
            fullfillmentList.get(position).setExpandStatus(8);
            notifyDataSetChanged();
        } else if (fullfillmentList.get(position).getExpandStatus() == 8) {
            multipleStatusCheckDummy(position);
        }
    }

    private void multipleStatusCheckDummy(int position) {
        boolean full = false;
        boolean partial = false;
        boolean notAvailable = false;
        int notFlag = 0;
        int fullFalg = 0;

        if (pickupProcessMvpView.productList() != null && pickupProcessMvpView.productList().size() > 0) {
            for (int i = 0; i < pickupProcessMvpView.productList().size(); i++) {
                for (int k = 0; k < pickupProcessMvpView.productList().get(i).size(); k++) {
                    for (int j = 0; j < pickupProcessMvpView.productsNextPosReturn().size(); j++) {
                        if (pickupProcessMvpView.productList().get(i).get(k).getProductId().equalsIgnoreCase(pickupProcessMvpView.productsNextPosReturn().get(j).getProductId())) {
                            if (pickupProcessMvpView.productsNextPosReturn().get(j).getStatus() != null) {
                                if (pickupProcessMvpView.productsNextPosReturn().get(j).getStatus().equalsIgnoreCase(pickupProcessMvpView.productList().get(i).get(k).getStatus()))
                                    pickupProcessMvpView.productsNextPosReturn().get(j).setStatus(pickupProcessMvpView.productList().get(i).get(k).getStatus());
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < pickupProcessMvpView.productsNextPosReturn().size(); i++) {
            if (pickupProcessMvpView.productsNextPosReturn().get(i).getStatus() != null) {
                if (pickupProcessMvpView.productsNextPosReturn().get(i).getStatus().equalsIgnoreCase("Partial")) {
                    partial = true;
                    notAvailable = false;
                    full = false;
                } else if (pickupProcessMvpView.productsNextPosReturn().get(i).getStatus().equalsIgnoreCase("NotAvailable")) {
                    partial = true;
                    notFlag = notFlag + 1;
                    if (notFlag == pickupProcessMvpView.productsNextPosReturn().size()) {
                        notAvailable = true;
                        partial = false;
                        full = false;
                    }
                } else if (pickupProcessMvpView.productsNextPosReturn().get(i).getStatus().equalsIgnoreCase("Full")) {
                    partial = true;
                    fullFalg = fullFalg + 1;
                    if (fullFalg == pickupProcessMvpView.productsNextPosReturn().size()) {
                        full = true;
                        partial = false;
                        notAvailable = false;
                    }
                }
            }
        }

        if (partial) {
            fullfillmentList.get(position).setExpandStatus(3);
            notifyDataSetChanged();
        } else if (notAvailable) {
            fullfillmentList.get(position).setExpandStatus(5);
            notifyDataSetChanged();
        } else if (full) {
            fullfillmentList.get(position).setExpandStatus(7);
            notifyDataSetChanged();
        } else {
            if (fullfillmentList.get(position).getExpandStatus() == 9) {
                fullfillmentList.get(position).setExpandStatus(8);
            } else {
                fullfillmentList.get(position).setExpandStatus(2);
            }
            notifyDataSetChanged();
        }
    }

    private void multipleStatusCheck(List<RackAdapter.RackBoxModel.ProductData> productDataList, int position) {
        boolean full = false;
        boolean partial = false;
        boolean notAvailable = false;
        int notFlag = 0;
        int fullFalg = 0;


        if (pickupProcessMvpView.productList() != null && pickupProcessMvpView.productList().size() > 0) {
            for (int i = 0; i < pickupProcessMvpView.productList().size(); i++) {
                for (int k = 0; k < pickupProcessMvpView.productList().get(i).size(); k++) {
                    for (int j = 0; j < productDataList.size(); j++) {
                        if (pickupProcessMvpView.productList().get(i).get(k).getProductId().equalsIgnoreCase(productDataList.get(j).getProductId())) {
                            if (productDataList.get(j).getStatus() != null) {
                                if (productDataList.get(j).getStatus().equalsIgnoreCase(pickupProcessMvpView.productList().get(i).get(k).getStatus()))
                                    productDataList.get(j).setStatus(pickupProcessMvpView.productList().get(i).get(k).getStatus());
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
            notifyDataSetChanged();
        } else if (notAvailable) {
            fullfillmentList.get(position).setExpandStatus(5);
            notifyDataSetChanged();
        } else if (full) {
            fullfillmentList.get(position).setExpandStatus(7);
            notifyDataSetChanged();
        } else {
            if (fullfillmentList.get(position).getExpandStatus() == 9) {
                fullfillmentList.get(position).setExpandStatus(8);
            } else {
                fullfillmentList.get(position).setExpandStatus(2);
            }
            notifyDataSetChanged();
        }
    }

    private void firstTimeMultipleStatusCheck(List<RackAdapter.RackBoxModel.ProductData> productDataList, int position, AdapterOrderBinding rackBinding) {
        boolean full = false;
        boolean partial = false;
        boolean notAvailable = false;
        int notFlag = 0;
        int fullFalg = 0;

        if (pickupProcessMvpView.productList() != null && pickupProcessMvpView.productList().size() > 0) {
            for (int i = 0; i < pickupProcessMvpView.productList().size(); i++) {
                for (int k = 0; k < pickupProcessMvpView.productList().get(i).size(); k++) {
                    for (int j = 0; j < productDataList.size(); j++) {
                        if (pickupProcessMvpView.productList().get(i).get(k).getProductId().equalsIgnoreCase(productDataList.get(j).getProductId())) {
                            if (productDataList.get(j).getStatus() != null) {
                                if (productDataList.get(j).getStatus().equalsIgnoreCase(pickupProcessMvpView.productList().get(i).get(k).getStatus()))
                                    productDataList.get(j).setStatus(pickupProcessMvpView.productList().get(i).get(k).getStatus());
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
            rackBinding.rackChild2Layout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_bg));
            rackBinding.start.setVisibility(View.GONE);
            rackBinding.status.setText("Partial");
            rackBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_partial));
            rackBinding.statusLayout.setVisibility(View.VISIBLE);
            rackBinding.rackChild2Layout.setVisibility(View.GONE);
            rackBinding.rackChild2Layout.setBackground(null);
        } else if (notAvailable) {
            fullfillmentList.get(position).setExpandStatus(5);
            rackBinding.rackChild2Layout.setBackgroundColor(context.getResources().getColor(R.color.trans_red));
            rackBinding.start.setVisibility(View.GONE);
            rackBinding.status.setText("Not Available");
            rackBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_not_available));
            rackBinding.statusLayout.setVisibility(View.VISIBLE);
            rackBinding.rackChild2Layout.setVisibility(View.GONE);
            rackBinding.rackChild2Layout.setBackground(null);
        } else if (full) {
            fullfillmentList.get(position).setExpandStatus(7);
            rackBinding.rackChild2Layout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_green));
            rackBinding.start.setVisibility(View.GONE);
            rackBinding.status.setText("Full");
            rackBinding.statusIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_circle_tick));
            rackBinding.statusLayout.setVisibility(View.VISIBLE);
            rackBinding.rackChild2Layout.setVisibility(View.GONE);
            rackBinding.rackChild2Layout.setBackground(null);
//        } else {
//            if (rackDataFilteredList.get(position).getExpandStatus() == 9) {
//                rackDataFilteredList.get(position).setExpandStatus(8);
//            } else {
//                rackDataFilteredList.get(position).setExpandStatus(2);
//            }
//            notifyDataSetChanged();
        }
    }

    private void completedViewCheck(List<RackAdapter.RackBoxModel.ProductData> productDataList, int position, AdapterOrderBinding orderBinding) {
        int completedFlag = 0;
        if (pickupProcessMvpView.productList() != null && pickupProcessMvpView.productList().size() > 0) {
            for (int i = 0; i < pickupProcessMvpView.productList().size(); i++) {
                for (int k = 0; k < pickupProcessMvpView.productList().get(i).size(); k++) {
                    for (int j = 0; j < productDataList.size(); j++) {
                        if (pickupProcessMvpView.productList().get(i).get(k).getProductId().equalsIgnoreCase(productDataList.get(j).getProductId())) {
                            if (pickupProcessMvpView.productList().get(i).get(k).isProductStatusFillingUpdate()) {
                                productDataList.get(j).setProductStatusFillingUpdate(pickupProcessMvpView.productList().get(i).get(k).isProductStatusFillingUpdate());
                            }
                        }
                    }
                }
            }
        }
        if (pickupProcessMvpView.productList() != null && pickupProcessMvpView.productList().size() > 0) {
            for (int i = 0; i < productDataList.size(); i++) {
                if (productDataList.get(i).isProductStatusFillingUpdate()) {
                    completedFlag = completedFlag + 1;
                }
            }

            if (completedFlag == productDataList.size()) {
                if (fullfillmentList.get(position).getExpandStatus() != 5 && fullfillmentList.get(position).getExpandStatus() != 6 && fullfillmentList.get(position).getExpandStatus() != 7) {
                    fullfillmentList.get(position).setExpandStatus(5);
                    orderBinding.orderChildLayout.setBackground(context.getResources().getDrawable(R.drawable.square_stroke_green));
                    orderBinding.start.setVisibility(View.GONE);
                    orderBinding.status.setText("Completed");
                    orderBinding.statusIcon.setVisibility(View.VISIBLE);
                    orderBinding.statusLayout.setVisibility(View.VISIBLE);
                    orderBinding.rackChild2Layout.setVisibility(View.GONE);
                    orderBinding.rackChild2Layout.setBackground(null);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return fullfillmentList.size();
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

            private boolean productStatusFillingUpdate;

            private boolean finalStatusUpdate;

            public boolean isFinalStatusUpdate() {
                return finalStatusUpdate;
            }

            public void setFinalStatusUpdate(boolean finalStatusUpdate) {
                this.finalStatusUpdate = finalStatusUpdate;
            }

            public boolean isProductStatusFillingUpdate() {
                return productStatusFillingUpdate;
            }

            public void setProductStatusFillingUpdate(boolean productStatusFillingUpdate) {
                this.productStatusFillingUpdate = productStatusFillingUpdate;
            }


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
