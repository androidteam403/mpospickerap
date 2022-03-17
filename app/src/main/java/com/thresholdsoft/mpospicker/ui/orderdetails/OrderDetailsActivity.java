package com.thresholdsoft.mpospicker.ui.orderdetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityOrderDetailssBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.orderdetails.adapter.OrderDetailsAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.io.Serializable;

import javax.inject.Inject;

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsMvpView {

    @Inject
    OrderDetailsMvpPresenter<OrderDetailsMvpView> mPresenter;
    ActivityOrderDetailssBinding activityOrderDetailssBinding;
    private RacksDataResponse.FullfillmentDetail racksDataResponse;
    OrderDetailsAdapter orderDetailsAdapter;

    public static Intent getStartActivity(Context context) {
        return new Intent(context, OrderDetailsActivity.class);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOrderDetailssBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_detailss);
        getActivityComponent().inject(OrderDetailsActivity.this);
        mPresenter.onAttach(OrderDetailsActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        activityOrderDetailssBinding.setCallback(mPresenter);
        if (getIntent() != null) {
            racksDataResponse = (RacksDataResponse.FullfillmentDetail) getIntent().getSerializableExtra("fullfillmentDetails");
        }
        activityOrderDetailssBinding.headerFullfillmentId.setText("Fullfillment ID: " + racksDataResponse.getFullfillmentId());
        orderDetailsAdapter = new OrderDetailsAdapter(racksDataResponse.getProducts(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        activityOrderDetailssBinding.orderDetailsRecycler.setLayoutManager(mLayoutManager);
        activityOrderDetailssBinding.orderDetailsRecycler.setAdapter(orderDetailsAdapter);


    }

    @Override
    public void onSelectContinue() {
        Intent i = new Intent();
        i.putExtra("FullfillmentID", (Serializable) racksDataResponse);
        i.putExtra("isSelect", true);
        setResult(RESULT_OK, i);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public void onClickBackIcon() {
        onBackPressed();
    }
}