package com.thresholdsoft.mpospicker.ui.orderdetails;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

public interface OrderDetailsMvpPresenter<V extends OrderDetailsMvpView> extends MvpPresenter<V> {

    void onSelectContinue();

    void onClickBackIcon();
}
