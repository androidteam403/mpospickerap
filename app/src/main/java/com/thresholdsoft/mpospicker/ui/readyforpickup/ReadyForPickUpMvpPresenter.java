package com.thresholdsoft.mpospicker.ui.readyforpickup;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

public interface ReadyForPickUpMvpPresenter<V extends ReadyForPickUpMvpView> extends MvpPresenter<V> {
    void onClickStartPickup();

    void onClickBack();
}
