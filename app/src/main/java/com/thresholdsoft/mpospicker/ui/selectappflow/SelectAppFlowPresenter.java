package com.thresholdsoft.mpospicker.ui.selectappflow;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SelectAppFlowPresenter<V extends SelectAppFlowMvpView> extends BasePresenter<V>
        implements SelectAppFlowMvpPresenter<V> {
    @Inject
    public SelectAppFlowPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onClickContinue() {
        getMvpView().onClickContinue();
    }
}
