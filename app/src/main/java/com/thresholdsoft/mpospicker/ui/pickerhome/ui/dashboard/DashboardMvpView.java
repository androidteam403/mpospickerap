package com.thresholdsoft.mpospicker.ui.pickerhome.ui.dashboard;

import com.thresholdsoft.mpospicker.ui.base.MvpView;

public interface DashboardMvpView extends MvpView {
    void onClickOpenOrders();

    void onClickFromDate();

    void onClickToDate();

    void onClickToday();

    void onClickWeekly();

    void onClickMonthly();

    void onClickYearly();
}
