package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityPickupVerificationBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.adapter.PickUpVerificationAdapter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.dialog.UpdateStatusDialog;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.dialog.VerificationStatusDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PickUpVerificationActivity extends BaseActivity implements PickUpVerificationMvpView {

    @Inject
    PickUpVerificationMvpPresenter<PickUpVerificationMvpView> mpresenter;
    private ActivityPickupVerificationBinding activityPickupVerificationBinding;
    private List<PickPackProductsData> pickPackProductsDataList = new ArrayList<>();
    private PickUpVerificationAdapter pickUpVerificationAdapter;
    private int partialView;
    private boolean updateVerified;
    boolean reverification;
    private boolean clickableRecycle = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPickupVerificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_pickup_verification);
        getActivityComponent().inject(this);
        mpresenter.onAttach(PickUpVerificationActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        activityPickupVerificationBinding.setCallback(mpresenter);
        if (getIntent() != null) {
            partialView = (int) getIntent().getIntExtra("partial", 0);
        }

        getPickPackDataList();

        pickUpVerificationAdapter = new PickUpVerificationAdapter(this, pickPackProductsDataList, this);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityPickupVerificationBinding.recyclerView.setLayoutManager(mLayoutManager1);
        activityPickupVerificationBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityPickupVerificationBinding.recyclerView.setAdapter(pickUpVerificationAdapter);

        if (partialView == 1) {
            activityPickupVerificationBinding.parent.setBackgroundColor(getResources().getColor(R.color.white));
            activityPickupVerificationBinding.parent.setAlpha((float) 0.4);
            activityPickupVerificationBinding.statusText.setText("Partial");
            activityPickupVerificationBinding.partialStatusColor.setVisibility(View.VISIBLE);
            activityPickupVerificationBinding.warningText.setVisibility(View.VISIBLE);
            clickableRecycle = false;
        } else {
            activityPickupVerificationBinding.parent.setBackground(null);
            activityPickupVerificationBinding.statusText.setText("Full");
            activityPickupVerificationBinding.partialStatusColor.setVisibility(View.GONE);
            activityPickupVerificationBinding.warningText.setVisibility(View.GONE);
        }

    }

    private void getPickPackDataList() {
        PickPackProductsData pickPackProductsData = new PickPackProductsData();
        pickPackProductsData.setProduct("Ascoril Plus Syrup Sugar Free");
        pickPackProductsData.setQty("1/1");
        pickPackProductsData.setProductStatus(0);
        pickPackProductsDataList.add(pickPackProductsData);

        pickPackProductsData = new PickPackProductsData();
        pickPackProductsData.setProduct("Allegra 180mg Tablet");
        pickPackProductsData.setQty("10/10");
        pickPackProductsData.setProductStatus(2);
        pickPackProductsDataList.add(pickPackProductsData);

        pickPackProductsData = new PickPackProductsData();
        pickPackProductsData.setProduct("Amoxyclav 625 Tablet");
        pickPackProductsData.setQty("10/10");
        pickPackProductsData.setProductStatus(1);
        pickPackProductsDataList.add(pickPackProductsData);

        pickPackProductsData = new PickPackProductsData();
        pickPackProductsData.setProduct("Augmentin 625 Duo Tablet");
        pickPackProductsData.setQty("20/20");
        pickPackProductsData.setProductStatus(0);
        pickPackProductsDataList.add(pickPackProductsData);

        pickPackProductsData = new PickPackProductsData();
        pickPackProductsData.setProduct("Azithral 500 Tablet");
        pickPackProductsData.setQty("15/15");
        pickPackProductsData.setProductStatus(0);
        pickPackProductsDataList.add(pickPackProductsData);


        pickPackProductsData = new PickPackProductsData();
        pickPackProductsData.setProduct("Asocril LS Syrup");
        pickPackProductsData.setQty("1/1");
        pickPackProductsData.setProductStatus(0);
        pickPackProductsDataList.add(pickPackProductsData);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onItemClick(int position, PickPackProductsData pickPackProductsData) {
        UpdateStatusDialog updateStatusDialog = new UpdateStatusDialog(PickUpVerificationActivity.this, pickPackProductsData);
        updateStatusDialog.setPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityPickupVerificationBinding.sendReVer.setBackgroundResource(R.color.red);
                activityPickupVerificationBinding.sendReVer.setTextColor(getResources().getColor(R.color.white));
                activityPickupVerificationBinding.pickVerified.setBackgroundResource(R.color.yellow);
                activityPickupVerificationBinding.pickVerified.setTextColor(getResources().getColor(R.color.black));
                updateVerified = true;
                updateStatusDialog.dismiss();
            }
        });
        updateStatusDialog.setNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatusDialog.dismiss();
            }
        });
        updateStatusDialog.show();
    }

    @SuppressLint("Range")
    @Override
    public void onPartialWarningYesClick() {
        activityPickupVerificationBinding.statusText.setText("Full");
        activityPickupVerificationBinding.partialStatusColor.setVisibility(View.GONE);
        activityPickupVerificationBinding.warningText.setVisibility(View.GONE);
        activityPickupVerificationBinding.parent.setBackgroundColor(0);
        activityPickupVerificationBinding.parent.setAlpha(4);
        activityPickupVerificationBinding.parent.setClickable(true);
        clickableRecycle = true;
    }

    @Override
    public void onPartialWarningNoClick() {
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClickReVerificatio() {
        if (updateVerified) {
            reverification = true;
            activityPickupVerificationBinding.sendReVer.setClickable(true);
            VerificationStatusDialog verificationStatusDialog = new VerificationStatusDialog(PickUpVerificationActivity.this, reverification);
            verificationStatusDialog.setPositiveListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    verificationStatusDialog.dismiss();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
            });
            verificationStatusDialog.setNegativeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verificationStatusDialog.dismiss();
                }
            });

            verificationStatusDialog.show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClickVerification() {
        if (updateVerified) {
            reverification = false;
            activityPickupVerificationBinding.pickVerified.setClickable(true);
            VerificationStatusDialog verificationStatusDialog = new VerificationStatusDialog(PickUpVerificationActivity.this, reverification);
            verificationStatusDialog.setPositiveListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    verificationStatusDialog.dismiss();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
            });
            verificationStatusDialog.setNegativeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verificationStatusDialog.dismiss();
                }
            });

            verificationStatusDialog.show();
        }
    }

    @Override
    public void onBackClick() {
        onBackPressed();
    }

    @Override
    public boolean recyclerItemClickableStatus() {
        return clickableRecycle;
    }

    public class PickPackProductsData {
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
}
