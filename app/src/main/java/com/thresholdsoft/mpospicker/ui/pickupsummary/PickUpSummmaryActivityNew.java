package com.thresholdsoft.mpospicker.ui.pickupsummary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityPickUpSummaryBinding;
import com.thresholdsoft.mpospicker.databinding.DialogFarwardtoPackerAlertBinding;
import com.thresholdsoft.mpospicker.databinding.DialogFarwardtoPackerBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.billerflow.billerOrdersScreen.BillerOrdersActivity;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersActivity;
import com.thresholdsoft.mpospicker.ui.pickerhome.PickerNavigationActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.OrderAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.ui.pickupsummary.adapter.SummaryFullfillmentAdapter;
import com.thresholdsoft.mpospicker.ui.pickupsummarydetails.PickupSummaryDetailsActivity;
import com.thresholdsoft.mpospicker.ui.readyforpickup.scanner.ScannerActivity;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

public class PickUpSummmaryActivityNew extends BaseActivity implements PickUpSummaryMvpView {
    @Inject
    PickUpSummaryMvpPresenter<PickUpSummaryMvpView> mPresenter;
    ActivityPickUpSummaryBinding activityPickUpSummaryBinding;

    private SummaryFullfillmentAdapter summaryFullfillmentAdapter;
    private List<RacksDataResponse.FullfillmentDetail> racksDataResponse;
    List<List<RackAdapter.RackBoxModel.ProductData>> rackListOfListFiltered;
    List<List<OrderAdapter.RackBoxModel.ProductData>> fullfillmentListOfListFiltered;
    String time, stopWatch;

    public static Intent getStartActivity(Context context, List<RacksDataResponse.FullfillmentDetail> racksDataResponse, String time, String stopWatch) {

        Intent intent = new Intent(context, PickUpSummmaryActivityNew.class);
        intent.putExtra("rackDataResponse", (Serializable) racksDataResponse);
//        intent.putExtra("rackListOfListFiltered", myJson);
//        intent.putExtra("fullListOfListFiltered", fullFillJson);
        intent.putExtra("time", time);
        intent.putExtra("stopWatch", stopWatch);
        return intent;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPickUpSummaryBinding = DataBindingUtil.setContentView(this, R.layout.activity_pick_up_summary);
        getActivityComponent().inject(this);
        mPresenter.onAttach(PickUpSummmaryActivityNew.this);
        setUp();
    }

    @Override
    protected void setUp() {
        activityPickUpSummaryBinding.setCallback(mPresenter);
        if (getIntent() != null) {
            racksDataResponse = (List<RacksDataResponse.FullfillmentDetail>) getIntent().getSerializableExtra("rackDataResponse");

//            Gson gson = new Gson();
//            String json = getIntent().getStringExtra("rackListOfListFiltered");
//            Type type = new TypeToken<List<List<RackAdapter.RackBoxModel.ProductData>>>() {
//            }.getType();
//            if (rackListOfListFiltered != null) {
//                rackListOfListFiltered.clear();
//            }
//            rackListOfListFiltered = gson.fromJson(json, type);
//
//            Gson gson1 = new Gson();
//            String json1 = getIntent().getStringExtra("fullListOfListFiltered");
//            Type type1 = new TypeToken<List<List<OrderAdapter.RackBoxModel.ProductData>>>() {
//            }.getType();
//            if (fullfillmentListOfListFiltered != null) {
//                fullfillmentListOfListFiltered.clear();
//            }
//            fullfillmentListOfListFiltered = gson1.fromJson(json1, type1);

            time = (String) getIntent().getStringExtra("time");
            stopWatch = (String) getIntent().getStringExtra("stopWatch");

            activityPickUpSummaryBinding.time.setText(time);
            activityPickUpSummaryBinding.timer.setText(stopWatch);

        }

        activityPickUpSummaryBinding.forwardToPacker.setOnClickListener(v -> {
            forwardtoPacker();
        });

//        if (rackListOfListFiltered != null)
        summaryFullfillmentAdapter = new SummaryFullfillmentAdapter(PickUpSummmaryActivityNew.this, racksDataResponse, PickUpSummmaryActivityNew.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PickUpSummmaryActivityNew.this);
        activityPickUpSummaryBinding.rackRecycler.setLayoutManager(mLayoutManager);
        activityPickUpSummaryBinding.rackRecycler.setAdapter(summaryFullfillmentAdapter);
        activityPickUpSummaryBinding.headerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public List<List<OrderAdapter.RackBoxModel.ProductData>> fullfilListOfList() {
        return fullfillmentListOfListFiltered;
    }

    @Override
    public List<List<RackAdapter.RackBoxModel.ProductData>> productList() {
        return rackListOfListFiltered;
    }

    String fullCount, partialCount, notCount;

    @Override
    public String fullCount(String fullCount) {
        this.fullCount = fullCount;
        return fullCount;
    }

    @Override
    public String partialCount(String partialCount) {
        this.partialCount = partialCount;
        return partialCount;
    }

    @Override
    public String notAvailable(String notAvailableCount) {
        this.notCount = notAvailableCount;
        return notAvailableCount;
    }

    @Override
    public void onClickItem(int pos) {
        startActivity(PickupSummaryDetailsActivity.getStartIntent(this, racksDataResponse.get(pos)));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void onClickScanCode() {
        new IntentIntegrator(this).setCaptureActivity(ScannerActivity.class).initiateScan();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void forwardtoPacker() {
        Dialog dialog = new Dialog(this);
        DialogFarwardtoPackerAlertBinding updateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.dialog_farwardto_packer_alert, null, false);
        dialog.setContentView(updateStatusBinding.getRoot());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        if (fullCount != null)
            updateStatusBinding.fullCount.setText(fullCount);
        else
            updateStatusBinding.fullCount.setText("0");
        if (partialCount != null)
            updateStatusBinding.partialCount.setText(partialCount);
        else
            updateStatusBinding.partialCount.setText("0");
        if (notCount != null)
            updateStatusBinding.notAvailableCount.setText(notCount);
        else
            updateStatusBinding.notAvailableCount.setText("0");

        updateStatusBinding.no.setOnClickListener(v -> {
            dialog.dismiss();
        });
        updateStatusBinding.yes.setOnClickListener(v -> {
            dialog.dismiss();
            dialog.cancel();
            gotoOpenOrder();
        });
        dialog.show();

        updateStatusBinding.cancelIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void gotoOpenOrder() {
        Dialog dialog = new Dialog(this);
        DialogFarwardtoPackerBinding updateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.dialog_farwardto_packer, null, false);
        dialog.setContentView(updateStatusBinding.getRoot());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        updateStatusBinding.gotoOpenOrders.setOnClickListener(v -> {
            mPresenter.setFullfillmentData(racksDataResponse);
            mPresenter.setListOfListFullfillmentData(rackListOfListFiltered);
            Intent i = new Intent(PickUpSummmaryActivityNew.this, OpenOrdersActivity.class);
            startActivity(i);            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
