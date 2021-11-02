package com.thresholdsoft.mpospicker.ui.base;

import androidx.annotation.StringRes;

/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */
public interface MvpView {

    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void openLoginActivity();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

}

