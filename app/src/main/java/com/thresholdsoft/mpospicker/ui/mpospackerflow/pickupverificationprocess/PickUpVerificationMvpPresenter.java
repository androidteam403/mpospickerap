package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

public interface PickUpVerificationMvpPresenter<V extends PickUpVerificationMvpView> extends MvpPresenter<V> {
    void onPartialWarningYesClick();

    void onPartialWarningNoClick();

    void onClickReVerificatio();

    void onClickVerification();
}
