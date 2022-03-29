package com.thresholdsoft.mpospicker.ui.billerflow.billerOrdersScreen;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

public interface BillerOrdersMvpPresenter  <V extends BillerOrdersMvpView> extends MvpPresenter<V> {
    void onClickFilterIcon();

    void onRackApiCall();
}
