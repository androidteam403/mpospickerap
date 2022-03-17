package com.thresholdsoft.mpospicker.ui.pickupsummarydetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.PickupsummaryLayoutBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.ui.pickupsummarydetails.adapter.NewPickUpSummaryAdapter;

import java.util.List;

public class NewPickUpSummaryActivity extends AppCompatActivity {
    private RacksDataResponse.FullfillmentDetail racksDataResponse;
    public PickupsummaryLayoutBinding pickupsummaryLayoutBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         pickupsummaryLayoutBinding = DataBindingUtil.setContentView(this, R.layout.pickupsummary_layout);
        if (getIntent() != null) {
            racksDataResponse = (RacksDataResponse.FullfillmentDetail) getIntent().getSerializableExtra("rackDataResponse");


            pickupsummaryLayoutBinding.fullfilmentIdnumber.setText(racksDataResponse.getFullfillmentId());
            pickupsummaryLayoutBinding.TotalItems.setText(racksDataResponse.getTotalItems());
            pickupsummaryLayoutBinding.status.setText(racksDataResponse.getStatus());



         NewPickUpSummaryAdapter pickUpSummaryAdapter = new NewPickUpSummaryAdapter(this,  racksDataResponse.getProducts());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(NewPickUpSummaryActivity.this);
            pickupsummaryLayoutBinding.recyclerView.setLayoutManager(mLayoutManager);
            pickupsummaryLayoutBinding.recyclerView.setAdapter(pickUpSummaryAdapter);

        }
    }



}