package com.thresholdsoft.mpospicker.ui.pickupsummary;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PickUpSummaryPresenter<V extends PickUpSummaryMvpView> extends BasePresenter<V>
        implements PickUpSummaryMvpPresenter<V> {

    @Inject
    public PickUpSummaryPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void forwardtoPacker() {
        getMvpView().forwardtoPacker();
    }
}
