package com.thresholdsoft.mpospicker.ui.pickupsummarydetails;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PickUpSummaryDetailsPresenter<V extends PickUpSummaryDetailsMvpView> extends BasePresenter<V>
        implements PickUpSummaryDetailsMvpPresenter<V> {
    @Inject
    public PickUpSummaryDetailsPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onClickBack() {
        getMvpView().onClickBack();
    }
}
