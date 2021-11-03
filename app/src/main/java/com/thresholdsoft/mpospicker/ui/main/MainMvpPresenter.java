package com.thresholdsoft.mpospicker.ui.main;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
