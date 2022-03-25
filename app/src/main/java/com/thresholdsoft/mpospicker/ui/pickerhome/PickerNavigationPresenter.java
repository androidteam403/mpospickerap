package com.thresholdsoft.mpospicker.ui.pickerhome;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class PickerNavigationPresenter <V extends PickerNavigationMvpView> extends BasePresenter<V>
        implements PickerNavigationMvpPresenter<V> {
    public PickerNavigationPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }
}
