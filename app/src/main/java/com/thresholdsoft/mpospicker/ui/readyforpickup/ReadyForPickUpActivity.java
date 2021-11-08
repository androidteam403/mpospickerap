package com.thresholdsoft.mpospicker.ui.readyforpickup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityReadyForPickupBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessActivity;
import com.thresholdsoft.mpospicker.ui.readyforpickup.adapter.ReadyForPickUpAdapter;
import com.thresholdsoft.mpospicker.ui.readyforpickup.dialog.ScanQrCodeDialog;
import com.thresholdsoft.mpospicker.ui.readyforpickup.dialog.UnTagQrCodeDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ReadyForPickUpActivity extends BaseActivity implements ReadyForPickUpMvpView {

    @Inject
    ReadyForPickUpMvpPresenter<ReadyForPickUpMvpView> mPresenter;
    ActivityReadyForPickupBinding activityReadyForPickupBinding;
    private FullfillmentData fullfillmentData;
    private ReadyForPickUpAdapter readyForPickUpAdapter;
    List<FullfillmentData> fullfillmentDataList;

    public static Intent getStartActivity(Context context) {
        return new Intent(context, ReadyForPickUpActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReadyForPickupBinding = DataBindingUtil.setContentView(this, R.layout.activity_ready_for_pickup);
        getActivityComponent().inject(this);
        mPresenter.onAttach(ReadyForPickUpActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        activityReadyForPickupBinding.setCallback(mPresenter);
        Glide.with(this).load("https://apis.v35.dev.zeroco.de/zc-v3.1-fs-svc/2.0/apollo_rider/get/41B8F83052E720DA0FC28401C9BFAA90396DCB4FD14F508D641DBC42F5808C634160E6E9BDFF4D97E46A107F1185330BE9BE56FEC6E2C512EC7E08CAAA498D8FA633B599A9A34C9C97BCF338231C7AA91F16F94D257D61803FBC97DE5FEEACF62933C5F49DFFBE9EBADD5C68A6A9245EE277F7369BEBB4A75B56F81CDA296FE0F45824C81F0E7A9C29BA1E691D49C48BCB3E2586250A732BC0C95D8C9A1E1154C38FC1DFED04C09C36722BD70B9D0E10952C6B12C3EABEF551397B781F83118196C4F5899C1A7EBB728DE8B78537C55B735B4BEAE021E0391CB1ACE72296B00A8869B3AA7F4BF1674AC2BF9952BF39A67ABCA6DC6BF69C69CCC9C5766F79B2F9").circleCrop().into(activityReadyForPickupBinding.pickerImg);
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
                boolean isAlltagBox = true;
                for (FullfillmentData fullfillmentData : fullfillmentDataList)
                    if (!fullfillmentData.isTagBox())
                        isAlltagBox = false;
                if (isAlltagBox) {
                    activityReadyForPickupBinding.startPicking.setBackground(getResources().getDrawable(R.drawable.btn_signin_ripple_effect));
                    activityReadyForPickupBinding.startPicking.setTextColor(getResources().getColor(R.color.black));
                } else {
                    activityReadyForPickupBinding.startPicking.setBackground(getResources().getDrawable(R.drawable.btn_ripple_effect_grey));
                    activityReadyForPickupBinding.startPicking.setTextColor(getResources().getColor(R.color.text_color_grey));
                }
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
                boolean isAlltagBox = true;
                for (FullfillmentData fullfillmentData : fullfillmentDataList)
                    if (!fullfillmentData.isTagBox())
                        isAlltagBox = false;
                if (isAlltagBox) {
                    activityReadyForPickupBinding.startPicking.setBackground(getResources().getDrawable(R.drawable.btn_signin_ripple_effect));
                    activityReadyForPickupBinding.startPicking.setTextColor(getResources().getColor(R.color.black));
                } else {
                    activityReadyForPickupBinding.startPicking.setBackground(getResources().getDrawable(R.drawable.btn_ripple_effect_grey));
                    activityReadyForPickupBinding.startPicking.setTextColor(getResources().getColor(R.color.text_color_grey));
                }
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

    @Override
    public void onClickStartPickup() {
        boolean isAlltagBox = true;
        for (FullfillmentData fullfillmentData : fullfillmentDataList)
            if (!fullfillmentData.isTagBox())
                isAlltagBox = false;
        if (isAlltagBox) {
            startActivity(PickupProcessActivity.getStartActivity(this));
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        } else {
            Toast.makeText(this, "Tag All boxes", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickBack() {
        onBackPressed();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
