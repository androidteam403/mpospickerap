package com.thresholdsoft.mpospicker.ui.pickupprocess;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PickupProcessPresenter<V extends PickupProcessMvpView> extends BasePresenter<V>
        implements PickupProcessMvpPresenter<V> {
    @Inject
    public PickupProcessPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onClickBack() {
        getMvpView().onClickBack();
    }

    @Override
    public void onClickContinue() {
        getMvpView().onClickContinue();
    }
}
