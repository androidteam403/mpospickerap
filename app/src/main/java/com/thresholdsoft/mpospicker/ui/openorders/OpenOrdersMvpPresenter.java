package com.thresholdsoft.mpospicker.ui.openorders;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

public interface OpenOrdersMvpPresenter<V extends OpenOrdersMvpView> extends MvpPresenter<V> {
    void onClickContinue();

    void onRackApiCall();

    void onClickFilterIcon();
}
