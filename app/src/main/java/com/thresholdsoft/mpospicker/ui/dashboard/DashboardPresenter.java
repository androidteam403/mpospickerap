package com.thresholdsoft.mpospicker.ui.dashboard;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DashboardPresenter<V extends DashboardMvpView> extends BasePresenter<V>
        implements DashboardMvpPresenter<V> {
    @Inject
    public DashboardPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onClickOpenOrders() {
        getMvpView().onClickOpenOrders();
    }

    @Override
    public void onClickFromDate() {
        getMvpView().onClickFromDate();
    }

    @Override
    public void onClickToDate() {
        getMvpView().onClickToDate();
    }

    @Override
    public void onClickToday() {
        getMvpView().onClickToday();
    }

    @Override
    public void onClickWeekly() {
        getMvpView().onClickWeekly();
    }

    @Override
    public void onClickMonthly() {
        getMvpView().onClickMonthly();
    }

    @Override
    public void onClickYearly() {
        getMvpView().onClickYearly();
    }
}
