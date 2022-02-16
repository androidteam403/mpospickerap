package com.thresholdsoft.mpospicker.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.thresholdsoft.mpospicker.di.ActivityContext;
import com.thresholdsoft.mpospicker.di.PerActivity;
import com.thresholdsoft.mpospicker.ui.login.LoginMvpPresenter;
import com.thresholdsoft.mpospicker.ui.login.LoginMvpView;
import com.thresholdsoft.mpospicker.ui.login.LoginPresenter;
import com.thresholdsoft.mpospicker.ui.main.MainMvpPresenter;
import com.thresholdsoft.mpospicker.ui.main.MainMvpView;
import com.thresholdsoft.mpospicker.ui.main.MainPresenter;
import com.thresholdsoft.mpospicker.ui.main.RssAdapter;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpPresenter;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpView;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersPresenter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessMvpPresenter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessMvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessPresenter;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpMvpPresenter;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpMvpView;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpPresenter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersMvpPresenter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersMvpView;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersPresenter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationMvpPresenter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationMvpView;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationPresenter;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpPresenter;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpView;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersPresenter;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummaryMvpPresenter;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummaryMvpView;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummaryPresenter;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpMvpPresenter;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpMvpView;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpPresenter;
import com.thresholdsoft.mpospicker.utils.rx.AppSchedulerProvider;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */
@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    OpenOrdersMvpPresenter<OpenOrdersMvpView> provideOpenOrdersPresenter(OpenOrdersPresenter<OpenOrdersMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ReadyForPickUpMvpPresenter<ReadyForPickUpMvpView> readyForPickUpPresenter(ReadyForPickUpPresenter<ReadyForPickUpMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PickedUpOrdersMvpPresenter<PickedUpOrdersMvpView> pickedUpOrdersMvpPresenter(PickedUpOrdersPresenter<PickedUpOrdersMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    PickUpVerificationMvpPresenter<PickUpVerificationMvpView> pick(PickUpVerificationPresenter<PickUpVerificationMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PickUpSummaryMvpPresenter<PickUpSummaryMvpView> pickUpSummary(PickUpSummaryPresenter<PickUpSummaryMvpView> presenter) {
        return presenter;
    }

    @Provides
    RssAdapter provideRssAdapter() {
        return new RssAdapter(new ArrayList<>());
    }

    @Provides
    @PerActivity
    PickupProcessMvpPresenter<PickupProcessMvpView> providePickupProcessPresenter(PickupProcessPresenter<PickupProcessMvpView> presenter) {
        return presenter;
    }



}