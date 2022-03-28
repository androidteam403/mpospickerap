package com.thresholdsoft.mpospicker.ui.selectappflow;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

public interface SelectAppFlowMvpPresenter<V extends SelectAppFlowMvpView> extends MvpPresenter<V> {

    void onClickContinue();
}
