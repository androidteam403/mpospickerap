package com.thresholdsoft.mpospicker.ui.pickupprocess;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityPickupProcessBinding;
import com.thresholdsoft.mpospicker.databinding.DialogUpdateStatusBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.OrderAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummaryActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PickupProcessActivity extends BaseActivity implements PickupProcessMvpView {

    @Inject
    PickupProcessMvpPresenter<PickupProcessMvpView> mPresenter;
    private ActivityPickupProcessBinding pickupProcessBinding;
    private OrderAdapter orderAdapter;
    private RackAdapter rackAdapter;
    private List<RackAdapter.RackModel> rackModelList;

    public static Intent getStartActivity(Context context) {
        return new Intent(context, PickupProcessActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pickupProcessBinding = DataBindingUtil.setContentView(this, R.layout.activity_pickup_process);
        getActivityComponent().inject(this);
        mPresenter.onAttach(PickupProcessActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        pickupProcessBinding.setCallback(mPresenter);
        rackOrderCheckedListener();
        getRackModelList();
        Glide.with(this).load("https://apis.v35.dev.zeroco.de/zc-v3.1-fs-svc/2.0/apollo_rider/get/41B8F83052E720DA0FC28401C9BFAA90396DCB4FD14F508D641DBC42F5808C634160E6E9BDFF4D97E46A107F1185330BE9BE56FEC6E2C512EC7E08CAAA498D8FA633B599A9A34C9C97BCF338231C7AA91F16F94D257D61803FBC97DE5FEEACF62933C5F49DFFBE9EBADD5C68A6A9245EE277F7369BEBB4A75B56F81CDA296FE0F45824C81F0E7A9C29BA1E691D49C48BCB3E2586250A732BC0C95D8C9A1E1154C38FC1DFED04C09C36722BD70B9D0E10952C6B12C3EABEF551397B781F83118196C4F5899C1A7EBB728DE8B78537C55B735B4BEAE021E0391CB1ACE72296B00A8869B3AA7F4BF1674AC2BF9952BF39A67ABCA6DC6BF69C69CCC9C5766F79B2F9").circleCrop().into(pickupProcessBinding.pickerImg);
        rackAdapter = new RackAdapter(this, rackModelList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        pickupProcessBinding.rackRecycler.setLayoutManager(mLayoutManager);
        pickupProcessBinding.rackRecycler.setAdapter(rackAdapter);
    }

    public void getRackModelList() {
        rackModelList = new ArrayList<>();
        RackAdapter.RackModel rackModel = new RackAdapter.RackModel();
        rackModel.setRackId("G098-98-786");
        rackModel.setItemStatus(1);
        rackModel.setFullfillmentID("887766");
        rackModel.setTotalItems("6");
        rackModel.setBoxID("1");
        rackModelList.add(rackModel);

        rackModel = new RackAdapter.RackModel();
        rackModel.setRackId("G098-98-789");
        rackModel.setItemStatus(0);
        rackModel.setFullfillmentID("362548");
        rackModel.setTotalItems("22");
        rackModel.setBoxID("2");
        rackModelList.add(rackModel);

        rackModel = new RackAdapter.RackModel();
        rackModel.setRackId("G098-98-800");
        rackModel.setItemStatus(0);
        rackModel.setFullfillmentID("258521");
        rackModel.setTotalItems("52");
        rackModel.setBoxID("3");
        rackModelList.add(rackModel);

        rackModel = new RackAdapter.RackModel();
        rackModel.setRackId("G098-98-815");
        rackModel.setItemStatus(0);
        rackModel.setFullfillmentID("321569");
        rackModel.setTotalItems("1");
        rackModel.setBoxID("4");
        rackModelList.add(rackModel);

        rackModel = new RackAdapter.RackModel();
        rackModel.setRackId("G098-98-796");
        rackModel.setItemStatus(0);
        rackModel.setFullfillmentID("321569");
        rackModel.setTotalItems("1");
        rackModel.setBoxID("4");
        rackModelList.add(rackModel);

        rackModel = new RackAdapter.RackModel();
        rackModel.setRackId("G098-98-722");
        rackModel.setItemStatus(0);
        rackModel.setFullfillmentID("321569");
        rackModel.setTotalItems("1");
        rackModel.setBoxID("4");
        rackModelList.add(rackModel);
    }

    private void rackOrderCheckedListener() {
        pickupProcessBinding.rackOrderToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rackAdapter = new RackAdapter(PickupProcessActivity.this, rackModelList, PickupProcessActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PickupProcessActivity.this);
                pickupProcessBinding.rackRecycler.setLayoutManager(mLayoutManager);
                pickupProcessBinding.rackRecycler.setAdapter(rackAdapter);
                Toast.makeText(PickupProcessActivity.this, "true", Toast.LENGTH_SHORT).show();
            } else {
                orderAdapter = new OrderAdapter(PickupProcessActivity.this, rackModelList, PickupProcessActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PickupProcessActivity.this);
                pickupProcessBinding.rackRecycler.setLayoutManager(mLayoutManager);
                pickupProcessBinding.rackRecycler.setAdapter(orderAdapter);
                Toast.makeText(PickupProcessActivity.this, "false", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void radioButtonListener(DialogUpdateStatusBinding updateStatusBinding) {
        updateStatusBinding.fullPickedRadioOne.setOnClickListener(v -> {
            if (!updateStatusBinding.fullPickedRadio.isChecked()) {
                updateStatusBinding.fullPickedRadio.setChecked(true);
                updateStatusBinding.partiallyPickedRadio.setChecked(false);
                updateStatusBinding.notAvailableRadio.setChecked(false);
            }
        });
        updateStatusBinding.partiallyPickedRadioTwo.setOnClickListener(v -> {
            if (!updateStatusBinding.partiallyPickedRadio.isChecked()) {
                updateStatusBinding.fullPickedRadio.setChecked(false);
                updateStatusBinding.partiallyPickedRadio.setChecked(true);
                updateStatusBinding.notAvailableRadio.setChecked(false);
            }
        });
        updateStatusBinding.notAvailableRadioThree.setOnClickListener(v -> {
            if (!updateStatusBinding.notAvailableRadio.isChecked()) {
                updateStatusBinding.fullPickedRadio.setChecked(false);
                updateStatusBinding.partiallyPickedRadio.setChecked(false);
                updateStatusBinding.notAvailableRadio.setChecked(true);
            }
        });
        updateStatusBinding.qtyDecreament.setOnClickListener(v -> {
            if (Integer.parseInt(updateStatusBinding.qtyEdit.getText().toString().trim()) > 1) {
                updateStatusBinding.qtyEdit.setText(String.valueOf(Integer.parseInt(updateStatusBinding.qtyEdit.getText().toString().trim()) - 1));
            }
        });
        updateStatusBinding.qtyIncreament.setOnClickListener(v -> {
            updateStatusBinding.qtyEdit.setText(String.valueOf(Integer.parseInt(updateStatusBinding.qtyEdit.getText().toString().trim()) + 1));
        });
    }

    @Override
    public void onStatusClick() {
        Dialog dialog = new Dialog(this, R.style.fadeinandoutcustomDialog);
        DialogUpdateStatusBinding updateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.dialog_update_status, null, false);
        dialog.setContentView(updateStatusBinding.getRoot());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        radioButtonListener(updateStatusBinding);
        updateStatusBinding.dismissDialog.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    @Override
    public void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onClickContinue() {
        startActivity(PickUpSummaryActivity.getStartActivity(this));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
