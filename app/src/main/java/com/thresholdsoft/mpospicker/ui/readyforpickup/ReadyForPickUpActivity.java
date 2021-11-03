package com.thresholdsoft.mpospicker.ui.readyforpickup;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityReadyForPickupBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.readyforpickup.adapter.ReadyForPickUpAdapter;
import com.thresholdsoft.mpospicker.ui.readyforpickup.dialog.ScanQrCodeDialog;
import com.thresholdsoft.mpospicker.ui.readyforpickup.dialog.UnTagQrCodeDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ReadyForPickUpActivity extends BaseActivity implements ReadyForPickUpMvpView {

    @Inject
    ReadyForPickUpMvpPresenter<ReadyForPickUpMvpView> mpresenter;
    ActivityReadyForPickupBinding activityReadyForPickupBinding;
    private FullfillmentData fullfillmentData;
    private ReadyForPickUpAdapter readyForPickUpAdapter;
    List<FullfillmentData> fullfillmentDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReadyForPickupBinding = DataBindingUtil.setContentView(this, R.layout.activity_ready_for_pickup);
        setUp();
    }

    @Override
    protected void setUp() {
        fullfillmentDataList = new ArrayList<>();
        fullfillmentData = new FullfillmentData();
        fullfillmentData.setFullfillmentId("887766");
        fullfillmentData.setTotalItems("12");
        fullfillmentDataList.add(fullfillmentData);

        fullfillmentData = new FullfillmentData();
        fullfillmentData.setFullfillmentId("362548");
        fullfillmentData.setTotalItems("22");
        fullfillmentDataList.add(fullfillmentData);

        fullfillmentData = new FullfillmentData();
        fullfillmentData.setFullfillmentId("258521");
        fullfillmentData.setTotalItems("52");
        fullfillmentDataList.add(fullfillmentData);

        fullfillmentData = new FullfillmentData();
        fullfillmentData.setFullfillmentId("321569");
        fullfillmentData.setTotalItems("1");
        fullfillmentDataList.add(fullfillmentData);

        fullfillmentData = new FullfillmentData();
        fullfillmentData.setFullfillmentId("845632");
        fullfillmentData.setTotalItems("69");
        fullfillmentDataList.add(fullfillmentData);

        readyForPickUpAdapter = new ReadyForPickUpAdapter(this, fullfillmentDataList, this);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityReadyForPickupBinding.readyForPickupRecycleView.setLayoutManager(mLayoutManager1);
        activityReadyForPickupBinding.readyForPickupRecycleView.setItemAnimator(new DefaultItemAnimator());
        activityReadyForPickupBinding.readyForPickupRecycleView.setAdapter(readyForPickUpAdapter);
    }

    @Override
    public void onTagBoxClick(String fullfillmentId, int pos) {
        ScanQrCodeDialog scanQrCodeDialog = new ScanQrCodeDialog(ReadyForPickUpActivity.this, fullfillmentId);
        scanQrCodeDialog.setPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQrCodeDialog.dismiss();
                fullfillmentDataList.get(pos).setTagBox(true);
                fullfillmentDataList.get(pos).setScanView(true);
                readyForPickUpAdapter.notifyDataSetChanged();
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
    public void onDeleteClick(int pos, String fullfillmentId) {
        UnTagQrCodeDialog unTagQrCodeDialog = new UnTagQrCodeDialog(ReadyForPickUpActivity.this, fullfillmentId);
        unTagQrCodeDialog.setPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unTagQrCodeDialog.dismiss();
                fullfillmentDataList.get(pos).setTagBox(false);
                fullfillmentDataList.get(pos).setScanView(false);
                readyForPickUpAdapter.notifyDataSetChanged();
            }
        });
        unTagQrCodeDialog.setNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unTagQrCodeDialog.dismiss();
            }
        });
        unTagQrCodeDialog.show();
    }

    public class FullfillmentData {
        private String fullfillmentId;
        private String totalItems;
        private boolean tagBox;
        private boolean scanView;

        public boolean isScanView() {
            return scanView;
        }

        public void setScanView(boolean scanView) {
            this.scanView = scanView;
        }

        public String getFullfillmentId() {
            return fullfillmentId;
        }

        public void setFullfillmentId(String fullfillmentId) {
            this.fullfillmentId = fullfillmentId;
        }

        public String getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(String totalItems) {
            this.totalItems = totalItems;
        }

        public boolean isTagBox() {
            return tagBox;
        }

        public void setTagBox(boolean tagBox) {
            this.tagBox = tagBox;
        }
    }
}
