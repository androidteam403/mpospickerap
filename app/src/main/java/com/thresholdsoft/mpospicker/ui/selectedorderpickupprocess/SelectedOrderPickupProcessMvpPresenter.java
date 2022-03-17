package com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

public interface SelectedOrderPickupProcessMvpPresenter<V extends SelectedOrderPickupProcessMvpView> extends MvpPresenter<V> {
    void onClickBack();

    void onRackApiCall();

    void onClickStausIcon();
}
