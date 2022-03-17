package com.thresholdsoft.mpospicker.di.component;


import com.thresholdsoft.mpospicker.di.PerActivity;
import com.thresholdsoft.mpospicker.di.module.ActivityModule;
import com.thresholdsoft.mpospicker.ui.dashboard.DashboardActivity;
import com.thresholdsoft.mpospicker.ui.login.LoginActivity;
import com.thresholdsoft.mpospicker.ui.main.MainActivity;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersActivity;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationActivity;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessActivity;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummmaryActivityNew;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpActivity;
import com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.SelectedOrderPickupProcessActivity;

import dagger.Component;

/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(DashboardActivity dashboardActivity);

    void inject(OpenOrdersActivity openOrdersActivity);

    void inject(ReadyForPickUpActivity readyForPickUpActivity);

    void inject(PickupProcessActivity pickupProcessActivity);

    void inject(PickedUpOrdersActivity pickedUpOrdersActivity);

    void inject(PickUpVerificationActivity pickUpVerificationActivity);

    void inject(PickUpSummmaryActivityNew pickUpSummmaryActivityNew);

    void inject(SelectedOrderPickupProcessActivity selectedOrderPickupProcessActivity);
}