package com.thresholdsoft.mpospicker.ui.login;

import com.thresholdsoft.mpospicker.data.network.pojo.UserProfile;
import com.thresholdsoft.mpospicker.ui.base.MvpView;

public interface LoginMvpView extends MvpView {
    void onLoginSuccess(UserProfile mUser);

    String getEmail();

    String getPassword();

    void showInputError();

    void setPassword(String userId);

    void setEmail(String password);
}
