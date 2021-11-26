package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PickUpVerificationPresenter<V extends PickUpVerificationMvpView> extends BasePresenter<V>
        implements PickUpVerificationMvpPresenter<V> {

    @Inject
    public PickUpVerificationPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onPartialWarningYesClick() {
        getMvpView().onPartialWarningYesClick();
    }

    @Override
    public void onPartialWarningNoClick() {
        getMvpView().onPartialWarningNoClick();
    }

    @Override
    public void onClickReVerificatio() {
        getMvpView().onClickReVerificatio();
    }

    @Override
    public void onClickVerification() {
        getMvpView().onClickVerification();
    }

}
