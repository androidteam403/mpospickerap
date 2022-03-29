package com.thresholdsoft.mpospicker.ui.pickupsummarydetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityPickupSummaryDetailsBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.ui.pickupsummarydetails.adapter.PickUpSummaryDetailsProductsAdapter;

import javax.inject.Inject;

public class PickupSummaryDetailsActivity extends BaseActivity implements PickUpSummaryDetailsMvpView {

    @Inject
    PickUpSummaryDetailsMvpPresenter<PickUpSummaryDetailsMvpView> mPresenter;
    private ActivityPickupSummaryDetailsBinding pickupSummaryDetailsBinding;

    public static Intent getStartIntent(Context context, RacksDataResponse.FullfillmentDetail fullfillmentDetail) {
        Intent intent = new Intent(context, PickupSummaryDetailsActivity.class);
        intent.putExtra("FULLFILLMENT_DETAILS", fullfillmentDetail);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pickupSummaryDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_pickup_summary_details);
        getActivityComponent().inject(this);
        mPresenter.onAttach(PickupSummaryDetailsActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        pickupSummaryDetailsBinding.setCallback(mPresenter);
        if (getIntent() != null) {
            RacksDataResponse.FullfillmentDetail racksDataResponse = (RacksDataResponse.FullfillmentDetail) getIntent().getSerializableExtra("FULLFILLMENT_DETAILS");

            pickupSummaryDetailsBinding.fullfilmentIdnumber.setText(racksDataResponse.getFullfillmentId());
            pickupSummaryDetailsBinding.totalItems.setText(racksDataResponse.getTotalItems());

            PickUpSummaryDetailsProductsAdapter pickUpSummaryAdapter = new PickUpSummaryDetailsProductsAdapter(this, racksDataResponse.getProducts());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PickupSummaryDetailsActivity.this);
            pickupSummaryDetailsBinding.productListRecycler.setLayoutManager(mLayoutManager);
            pickupSummaryDetailsBinding.productListRecycler.setAdapter(pickUpSummaryAdapter);

        }
    }

    @Override
    public void onClickBack() {
        onBackPressed();
    }
}