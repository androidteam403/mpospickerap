package com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess;

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

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivitySelectedOrderPickupProcessBinding;
import com.thresholdsoft.mpospicker.databinding.DialogUpdateStatusBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.batchlist.BatchListActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.adapter.SelectedPickupProcessProductsAdapter;

import javax.inject.Inject;

public class SelectedOrderPickupProcessActivity extends BaseActivity implements SelectedOrderPickupProcessMvpView {

    @Inject
    SelectedOrderPickupProcessMvpPresenter<SelectedOrderPickupProcessMvpView> mPresenter;
    private ActivitySelectedOrderPickupProcessBinding selectedOrderPickupProcessBinding;
    private DialogUpdateStatusBinding dialogUpdateStatusBinding;

    public static Intent getStartIntent(Context mContext, RacksDataResponse.FullfillmentDetail fullfillmentDetail) {
        Intent intent = new Intent(mContext, SelectedOrderPickupProcessActivity.class);
        intent.putExtra("fullfillmentDetail", fullfillmentDetail);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedOrderPickupProcessBinding = DataBindingUtil.setContentView(this, R.layout.activity_selected_order_pickup_process);
        getActivityComponent().inject(this);
        mPresenter.onAttach(SelectedOrderPickupProcessActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        selectedOrderPickupProcessBinding.setCallback(mPresenter);
        mPresenter.onRackApiCall();
    }

    @Override
    public void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onRackApiCall() {

    }

    @Override
    public void onSuccessRackApi(RacksDataResponse racksDataResponse) {
        if (racksDataResponse != null && racksDataResponse.getFullfillmentDetails().get(0) != null) {
            SelectedPickupProcessProductsAdapter selectedPickupProcessProductsAdapter = new SelectedPickupProcessProductsAdapter(this, racksDataResponse.getFullfillmentDetails().get(0).getProducts(), this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            selectedOrderPickupProcessBinding.selectedPickupProcessProductList.setLayoutManager(mLayoutManager);
            selectedOrderPickupProcessBinding.selectedPickupProcessProductList.setAdapter(selectedPickupProcessProductsAdapter);
        }
    }

    @Override
    public void onClickStausIcon() {
        Dialog statusUpdateDialog = new Dialog(this, R.style.fadeinandoutcustomDialog);
        dialogUpdateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_update_status, null, false);
        dialogUpdateStatusBinding.setCallback(mPresenter);
        statusUpdateDialog.setContentView(dialogUpdateStatusBinding.getRoot());
        statusUpdateDialog.setCancelable(false);
        dialogUpdateStatusBinding.dismissDialog.setOnClickListener(view -> statusUpdateDialog.dismiss());
        statusUpdateDialog.show();
    }

    @Override
    public void onClickFullPicked() {
        checkAllFalse();
        dialogUpdateStatusBinding.fullPickedRadio.setChecked(true);
        dialogUpdateStatusBinding.fullDetails.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClickPartialPicked() {
        checkAllFalse();
        dialogUpdateStatusBinding.partiallyPickedRadio.setChecked(true);
        dialogUpdateStatusBinding.partialDetails.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClickNotAvailable() {
        checkAllFalse();
        dialogUpdateStatusBinding.notAvailableRadio.setChecked(true);
    }

    @Override
    public void onClickSkip() {
        checkAllFalse();
        dialogUpdateStatusBinding.skipRadioBtn.setChecked(true);
    }

    @Override
    public void onClickBatchDetails() {
        startActivity(BatchListActivity.getStartIntent(this));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void checkAllFalse() {
        dialogUpdateStatusBinding.fullPickedRadio.setChecked(false);
        dialogUpdateStatusBinding.partiallyPickedRadio.setChecked(false);
        dialogUpdateStatusBinding.notAvailableRadio.setChecked(false);
        dialogUpdateStatusBinding.skipRadioBtn.setChecked(false);

        dialogUpdateStatusBinding.fullDetails.setVisibility(View.GONE);
        dialogUpdateStatusBinding.partialDetails.setVisibility(View.GONE);

    }
}
