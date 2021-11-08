package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders;

import com.thresholdsoft.mpospicker.ui.base.MvpView;

public interface PickedUpOrdersMvpView extends MvpView {
    void startPickUp();
    void onClickScanCode();
    void onItemClick(int position, int status);
}
