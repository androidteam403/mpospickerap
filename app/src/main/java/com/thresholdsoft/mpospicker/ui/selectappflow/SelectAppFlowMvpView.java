package com.thresholdsoft.mpospicker.ui.selectappflow;

import com.thresholdsoft.mpospicker.ui.base.MvpView;

public interface SelectAppFlowMvpView extends MvpView {
    void onClickContinue();

    void onClickSelectAppFlowItem(int pos);
}
