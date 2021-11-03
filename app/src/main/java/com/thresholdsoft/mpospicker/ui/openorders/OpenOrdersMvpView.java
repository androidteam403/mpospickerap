package com.thresholdsoft.mpospicker.ui.openorders;

import com.thresholdsoft.mpospicker.ui.base.MvpView;

public interface OpenOrdersMvpView extends MvpView {
    void onFullfillmentItemClick(int pos);
    void onClickContinue();
}
