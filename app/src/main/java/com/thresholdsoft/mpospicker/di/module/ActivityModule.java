package com.thresholdsoft.mpospicker.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.thresholdsoft.mpospicker.di.ActivityContext;
import com.thresholdsoft.mpospicker.di.PerActivity;
import com.thresholdsoft.mpospicker.ui.batchlist.BatchListMvpPresenter;
import com.thresholdsoft.mpospicker.ui.batchlist.BatchListMvpView;
import com.thresholdsoft.mpospicker.ui.batchlist.BatchListPresenter;
import com.thresholdsoft.mpospicker.ui.billerflow.billerOrdersScreen.BillerOrdersMvpPresenter;
import com.thresholdsoft.mpospicker.ui.billerflow.billerOrdersScreen.BillerOrdersMvpView;
import com.thresholdsoft.mpospicker.ui.billerflow.billerOrdersScreen.BillerOrdersPresenter;
import com.thresholdsoft.mpospicker.ui.billerflow.orderdetailsscreen.OrderDetailsScreenMvpPresenter;
import com.thresholdsoft.mpospicker.ui.billerflow.orderdetailsscreen.OrderDetailsScreenMvpView;
import com.thresholdsoft.mpospicker.ui.billerflow.orderdetailsscreen.OrderDetailsScreenPresenter;
import com.thresholdsoft.mpospicker.ui.login.LoginMvpPresenter;
import com.thresholdsoft.mpospicker.ui.login.LoginMvpView;
import com.thresholdsoft.mpospicker.ui.login.LoginPresenter;
import com.thresholdsoft.mpospicker.ui.main.MainMvpPresenter;
import com.thresholdsoft.mpospicker.ui.main.MainMvpView;
import com.thresholdsoft.mpospicker.ui.main.MainPresenter;
import com.thresholdsoft.mpospicker.ui.main.RssAdapter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersMvpPresenter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersMvpView;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersPresenter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationMvpPresenter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationMvpView;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationPresenter;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpPresenter;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpView;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersPresenter;
import com.thresholdsoft.mpospicker.ui.orderdetails.OrderDetailsMvpPresenter;
import com.thresholdsoft.mpospicker.ui.orderdetails.OrderDetailsMvpView;
import com.thresholdsoft.mpospicker.ui.orderdetails.OrderDetailsPresenter;
import com.thresholdsoft.mpospicker.ui.pickerhome.PickerNavigationMvpPresenter;
import com.thresholdsoft.mpospicker.ui.pickerhome.PickerNavigationMvpView;
import com.thresholdsoft.mpospicker.ui.pickerhome.PickerNavigationPresenter;
import com.thresholdsoft.mpospicker.ui.pickerhome.ui.dashboard.DashboardMvpPresenter;
import com.thresholdsoft.mpospicker.ui.pickerhome.ui.dashboard.DashboardMvpView;
import com.thresholdsoft.mpospicker.ui.pickerhome.ui.dashboard.DashboardPresenter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessMvpPresenter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessMvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.PickupProcessPresenter;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummaryMvpPresenter;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummaryMvpView;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummaryPresenter;
import com.thresholdsoft.mpospicker.ui.pickupsummarydetails.PickUpSummaryDetailsMvpPresenter;
import com.thresholdsoft.mpospicker.ui.pickupsummarydetails.PickUpSummaryDetailsMvpView;
import com.thresholdsoft.mpospicker.ui.pickupsummarydetails.PickUpSummaryDetailsPresenter;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpMvpPresenter;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpMvpView;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpPresenter;
import com.thresholdsoft.mpospicker.ui.selectappflow.SelectAppFlowMvpPresenter;
import com.thresholdsoft.mpospicker.ui.selectappflow.SelectAppFlowMvpView;
import com.thresholdsoft.mpospicker.ui.selectappflow.SelectAppFlowPresenter;
import com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.SelectedOrderPickupProcessMvpPresenter;
import com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.SelectedOrderPickupProcessMvpView;
import com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.SelectedOrderPickupProcessPresenter;
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
    DashboardMvpPresenter<DashboardMvpView> provideDashboardPresenter(DashboardPresenter<DashboardMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    OpenOrdersMvpPresenter<OpenOrdersMvpView> provideOpenOrdersPresenter(OpenOrdersPresenter<OpenOrdersMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    OrderDetailsMvpPresenter<OrderDetailsMvpView> OrderDetailsPresenter(OrderDetailsPresenter<OrderDetailsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ReadyForPickUpMvpPresenter<ReadyForPickUpMvpView> readyForPickUpPresenter(ReadyForPickUpPresenter<ReadyForPickUpMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    BillerOrdersMvpPresenter<BillerOrdersMvpView> billerOrdersPickupPresenter(BillerOrdersPresenter<BillerOrdersMvpView> presenter) {
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

    @Provides
    @PerActivity
    SelectedOrderPickupProcessMvpPresenter<SelectedOrderPickupProcessMvpView> provideSelectedOrderPickupProcessPresenter(SelectedOrderPickupProcessPresenter<SelectedOrderPickupProcessMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PickUpSummaryDetailsMvpPresenter<PickUpSummaryDetailsMvpView> providePickUpSummaryDetailsPresenter(PickUpSummaryDetailsPresenter<PickUpSummaryDetailsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    BatchListMvpPresenter<BatchListMvpView> provideBatchListPresenter(BatchListPresenter<BatchListMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PickerNavigationMvpPresenter<PickerNavigationMvpView> providePickerNavigationPresenter(PickerNavigationPresenter<PickerNavigationMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SelectAppFlowMvpPresenter<SelectAppFlowMvpView> provideSelectAppFlowPresenter(SelectAppFlowPresenter<SelectAppFlowMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    OrderDetailsScreenMvpPresenter<OrderDetailsScreenMvpView> orderDetailsScreenPresenter (OrderDetailsScreenPresenter<OrderDetailsScreenMvpView> presenter) {
        return presenter;
    }
}