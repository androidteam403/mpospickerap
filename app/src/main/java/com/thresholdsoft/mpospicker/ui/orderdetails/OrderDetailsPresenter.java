package com.thresholdsoft.mpospicker.ui.orderdetails;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpPresenter;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpView;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class OrderDetailsPresenter <V extends OrderDetailsMvpView> extends BasePresenter<V>
        implements OrderDetailsMvpPresenter<V> {
    @Inject
    public OrderDetailsPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSelectContinue() {
          getMvpView().onSelectContinue();
    }

}
