package com.thresholdsoft.mpospicker.ui.openorders;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public interface OpenOrdersMvpPresenter<V extends OpenOrdersMvpView> extends MvpPresenter<V> {
    void onClickContinue();

    void onRackApiCall();
}
