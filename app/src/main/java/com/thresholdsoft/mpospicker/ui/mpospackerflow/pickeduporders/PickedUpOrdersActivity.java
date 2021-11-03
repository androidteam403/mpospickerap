package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityPickedUpOrdersBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;

import javax.inject.Inject;

public class PickedUpOrdersActivity extends BaseActivity implements PickedUpOrdersMvpView {

    @Inject
    PickedUpOrdersMvpPresenter<PickedUpOrdersMvpView> mvpPresenter;
    private ActivityPickedUpOrdersBinding activityPickedUpOrdersBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPickedUpOrdersBinding = DataBindingUtil.setContentView(this, R.layout.activity_picked_up_orders);
        setUp();
    }

    @Override
    protected void setUp() {

    }
}
