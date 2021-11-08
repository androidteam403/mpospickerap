package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityPickedUpOrdersBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.adapter.PickedUpOrdersAdapter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationActivity;
import com.thresholdsoft.mpospicker.ui.readyforpickup.dialog.ScanQrCodeDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PickedUpOrdersActivity extends BaseActivity implements PickedUpOrdersMvpView {

    @Inject
    PickedUpOrdersMvpPresenter<PickedUpOrdersMvpView> mvpPresenter;
    private ActivityPickedUpOrdersBinding activityPickedUpOrdersBinding;
    private List<PickedUpFullfillmentData> fullfilmentModelList = new ArrayList<>();
    private PickedUpOrdersAdapter pickedUpOrdersAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPickedUpOrdersBinding = DataBindingUtil.setContentView(this, R.layout.activity_picked_up_orders);
        getActivityComponent().inject(this);
        mvpPresenter.onAttach(PickedUpOrdersActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        activityPickedUpOrdersBinding.setCallback(mvpPresenter);
        getFullfilmentModelList();
        activityPickedUpOrdersBinding.headerOrdersCount.setText("Total " + String.valueOf(fullfilmentModelList.size()) + " Orders");
        pickedUpOrdersAdapter = new PickedUpOrdersAdapter(this, fullfilmentModelList, this);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityPickedUpOrdersBinding.fullfilmentRecycler.setLayoutManager(mLayoutManager1);
        activityPickedUpOrdersBinding.fullfilmentRecycler.setItemAnimator(new DefaultItemAnimator());
        activityPickedUpOrdersBinding.fullfilmentRecycler.setAdapter(pickedUpOrdersAdapter);
    }

    @Override
    public void startPickUp() {

    }

    @Override
    public void onClickScanCode() {
        ScanQrCodeDialog scanQrCodeDialog = new ScanQrCodeDialog(PickedUpOrdersActivity.this, null);
        scanQrCodeDialog.setPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickedUpOrdersActivity.this, PickUpVerificationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                scanQrCodeDialog.dismiss();
            }
        });
        scanQrCodeDialog.setNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQrCodeDialog.dismiss();
            }
        });
        scanQrCodeDialog.show();
    }

    @Override
    public void onItemClick(int pos, int status) {
        Intent intent = new Intent(PickedUpOrdersActivity.this, PickUpVerificationActivity.class);
        intent.putExtra("partial", status);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void getFullfilmentModelList() {
        fullfilmentModelList = new ArrayList<>();

        PickedUpFullfillmentData fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(12);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("362548");
        fullfilmentModel.setTotalItems(22);
        fullfilmentModel.setOrderStatus(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("265689");
        fullfilmentModel.setTotalItems(5);
        fullfilmentModel.setOrderStatus(2);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(2);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(2);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(1);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(2);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(0);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new PickedUpFullfillmentData();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setOrderStatus(1);
        fullfilmentModelList.add(fullfilmentModel);
    }

    public class PickedUpFullfillmentData {
        private String fullfilmentId;
        private int totalItems;
        private int orderStatus;


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
