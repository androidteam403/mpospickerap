package com.thresholdsoft.mpospicker.ui.pickupprocess;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

public interface PickupProcessMvpPresenter<V extends PickupProcessMvpView> extends MvpPresenter<V> {


        void onClickBack();

    void onClickContinue();

    void onRackApiCall();
    void onClickStausIcon();

    void onClickFullPicked();

    void onClickPartialPicked();

    void onClickNotAvailable();
    void onClickBatchDetails();

    void onClickSkip();




}
