package com.thresholdsoft.mpospicker.ui.pickupsummary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityPickUpSummaryBinding;
import com.thresholdsoft.mpospicker.databinding.DialogFarwardtoPackerAlertBinding;
import com.thresholdsoft.mpospicker.databinding.DialogFarwardtoPackerBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersActivity;
import com.thresholdsoft.mpospicker.ui.pickupsummary.adapter.SummaryFullfillmentAdapter;
import com.thresholdsoft.mpospicker.ui.pickupsummary.adapter.SummaryProductsAdapter;

import java.util.ArrayList;
import java.util.List;

public class PickUpSummaryActivity extends BaseActivity implements PickUpSummaryMvpView {

    //    @Inject
    private PickUpSummaryMvpPresenter<PickUpSummaryMvpView> mpresenter;

    ActivityPickUpSummaryBinding activityPickUpSummaryBinding;
    private List<SummaryFullfillmentData> fullfilmentModelList = new ArrayList<>();
    private List<SummaryProductsData> pickPackProductsDataList = new ArrayList<>();
    private SummaryFullfillmentAdapter summaryFullfilmentAdapter;
    private SummaryProductsAdapter summaryProductsAdapter;

    public static Intent getStartActivity(Context context) {
        return new Intent(context, PickUpSummaryActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPickUpSummaryBinding = DataBindingUtil.setContentView(this, R.layout.activity_pick_up_summary);
//        getActivityComponent().inject(this);
//        mpresenter.onAttach(PickUpSummaryActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        activityPickUpSummaryBinding.setCallback(mpresenter);
        activityPickUpSummaryBinding.forwardToPacker.setOnClickListener(v -> {
            forwardtoPacker();
        });
        getPickPackDataList();
        getFullfilmentModelList();

        summaryProductsAdapter = new SummaryProductsAdapter(this, pickPackProductsDataList, this);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityPickUpSummaryBinding.prodRecyclerView.setLayoutManager(mLayoutManager2);
        activityPickUpSummaryBinding.prodRecyclerView.setItemAnimator(new DefaultItemAnimator());
        activityPickUpSummaryBinding.prodRecyclerView.setAdapter(summaryProductsAdapter);

        summaryFullfilmentAdapter = new SummaryFullfillmentAdapter(this, fullfilmentModelList, this);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityPickUpSummaryBinding.fullfillmentIdRecyclerView.setLayoutManager(mLayoutManager1);
        activityPickUpSummaryBinding.fullfillmentIdRecyclerView.setItemAnimator(new DefaultItemAnimator());
        activityPickUpSummaryBinding.fullfillmentIdRecyclerView.setAdapter(summaryFullfilmentAdapter);

    }

    private void getPickPackDataList() {
        SummaryProductsData pickPackProductsData = new SummaryProductsData();
        pickPackProductsData.setProduct("Ascoril Plus Syrup Sugar Free");
        pickPackProductsData.setQty("1/1");
        pickPackProductsData.setProductStatus(0);
        pickPackProductsDataList.add(pickPackProductsData);

        pickPackProductsData = new SummaryProductsData();
        pickPackProductsData.setProduct("Allegra 180mg Tablet");
        pickPackProductsData.setQty("10/10");
        pickPackProductsData.setProductStatus(2);
        pickPackProductsDataList.add(pickPackProductsData);

        pickPackProductsData = new SummaryProductsData();
        pickPackProductsData.setProduct("Amoxyclav 625 Tablet");
        pickPackProductsData.setQty("10/10");
        pickPackProductsData.setProductStatus(1);
        pickPackProductsDataList.add(pickPackProductsData);

        pickPackProductsData = new SummaryProductsData();
        pickPackProductsData.setProduct("Augmentin 625 Duo Tablet");
        pickPackProductsData.setQty("20/20");
        pickPackProductsData.setProductStatus(0);
        pickPackProductsDataList.add(pickPackProductsData);

        pickPackProductsData = new SummaryProductsData();
        pickPackProductsData.setProduct("Azithral 500 Tablet");
        pickPackProductsData.setQty("15/15");
        pickPackProductsData.setProductStatus(0);
        pickPackProductsDataList.add(pickPackProductsData);


        pickPackProductsData = new SummaryProductsData();
        pickPackProductsData.setProduct("Asocril LS Syrup");
        pickPackProductsData.setQty("1/1");
        pickPackProductsData.setProductStatus(0);
        pickPackProductsDataList.add(pickPackProductsData);
    }

    private void getFullfilmentModelList() {
        fullfilmentModelList = new ArrayList<>();

        SummaryFullfillmentData fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(12);
        fullfilmentModel.setOrderStatus(2);
        fullfilmentModel.setBoxId(2);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("362548");
        fullfilmentModel.setTotalItems(22);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModel.setBoxId(3);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("265689");
        fullfilmentModel.setTotalItems(5);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModel.setBoxId(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModel.setBoxId(4);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModel.setBoxId(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(1);
        fullfilmentModel.setBoxId(2);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setBoxId(3);
        fullfilmentModel.setOrderStatus(2);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModel.setBoxId(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(1);
        fullfilmentModel.setBoxId(4);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(2);
        fullfilmentModel.setBoxId(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setBoxId(2);
        fullfilmentModel.setOrderStatus(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModel.setBoxId(3);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(2);
        fullfilmentModel.setBoxId(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModel.setBoxId(4);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new SummaryFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setBoxId(1);
        fullfilmentModel.setOrderStatus(1);
        fullfilmentModelList.add(fullfilmentModel);
    }

    @Override
    public void forwardtoPacker() {
        Dialog dialog = new Dialog(this);
        DialogFarwardtoPackerAlertBinding updateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.dialog_farwardto_packer_alert, null, false);
        dialog.setContentView(updateStatusBinding.getRoot());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        updateStatusBinding.no.setOnClickListener(v -> {
            dialog.dismiss();
        });
        updateStatusBinding.yes.setOnClickListener(v -> {
            dialog.dismiss();
            dialog.cancel();
            gotoOpenOrder();
        });
        dialog.show();
    }

    private void gotoOpenOrder() {
        Dialog dialog = new Dialog(this);
        DialogFarwardtoPackerBinding updateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.dialog_farwardto_packer, null, false);
        dialog.setContentView(updateStatusBinding.getRoot());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        updateStatusBinding.gotoOpenOrders.setOnClickListener(v -> {
            startActivity(OpenOrdersActivity.getStartActivity(this));
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            dialog.dismiss();
        });
        dialog.show();
    }

    public static class SummaryProductsData {
        private String product;
        private String qty;
        private int productStatus;

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public int getProductStatus() {
            return productStatus;
        }

        public void setProductStatus(int productStatus) {
            this.productStatus = productStatus;
        }
    }

    public static class SummaryFullfillmentData {
        private String fullfilmentId;
        private int totalItems;
        private int boxId;
        private int orderStatus;

        public int getBoxId() {
            return boxId;
        }

        public void setBoxId(int boxId) {
            this.boxId = boxId;
        }

        public String getFullfilmentId() {
            return fullfilmentId;
        }

        public void setFullfilmentId(String fullfilmentId) {
            this.fullfilmentId = fullfilmentId;
        }

        public int getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(int totalItems) {
            this.totalItems = totalItems;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }
    }
}
