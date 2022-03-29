package com.thresholdsoft.mpospicker.ui.batchlist;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BatchListPresenter<V extends BatchListMvpView> extends BasePresenter<V>
        implements BatchListMvpPresenter<V> {
    @Inject
    public BatchListPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }
}
