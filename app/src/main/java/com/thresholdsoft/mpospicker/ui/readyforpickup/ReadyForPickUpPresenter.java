package com.thresholdsoft.mpospicker.ui.readyforpickup;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ReadyForPickUpPresenter<V extends ReadyForPickUpMvpView> extends BasePresenter<V>
        implements ReadyForPickUpMvpPresenter<V> {

    @Inject
    public ReadyForPickUpPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }
}