package com.thresholdsoft.mpospicker.ui.base;

/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */
public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(Throwable error);

    void setUserAsLoggedOut();
}

