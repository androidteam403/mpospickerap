package com.thresholdsoft.mpospicker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.BatchactivityBinding;
import com.thresholdsoft.mpospicker.databinding.FilterActivityBinding;
import com.thresholdsoft.mpospicker.databinding.PickupsummaryAdapterBinding;
import com.thresholdsoft.mpospicker.databinding.PickupsummaryLayoutBinding;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FilterActivityBinding filterActivityBinding= DataBindingUtil.setContentView(this, R.layout.filter_activity);

//        BatchactivityBinding filterActivityBinding= DataBindingUtil.setContentView(this, R.layout.batchactivity);
        PickupsummaryLayoutBinding pickupsummaryLayoutBinding=DataBindingUtil.setContentView(this,R.layout.pickupsummary_layout);
//        PickupsummaryAdapterBinding binding=DataBindingUtil.setContentView(this,R.layout.pickupsummary_adapter);

    }
}