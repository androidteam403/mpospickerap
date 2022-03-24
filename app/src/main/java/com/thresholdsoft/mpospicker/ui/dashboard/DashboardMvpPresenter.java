package com.thresholdsoft.mpospicker.ui.dashboard;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

public interface DashboardMvpPresenter<V extends DashboardMvpView> extends MvpPresenter<V> {
    void onClickOpenOrders();

    void onClickFromDate();

    void onClickToDate();

    void onClickToday();

    void onClickWeekly();

    void onClickMonthly();

    void onClickYearly();
}
