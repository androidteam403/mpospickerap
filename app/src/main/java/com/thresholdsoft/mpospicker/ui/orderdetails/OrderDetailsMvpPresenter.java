package com.thresholdsoft.mpospicker.ui.orderdetails;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;
import com.thresholdsoft.mpospicker.ui.openorders.OpenOrdersMvpView;

public interface OrderDetailsMvpPresenter <V extends OrderDetailsMvpView> extends MvpPresenter<V> {

    void onSelectContinue();

}
