package com.thresholdsoft.mpospicker.ui.login;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {
    void onLoginClick();
}
