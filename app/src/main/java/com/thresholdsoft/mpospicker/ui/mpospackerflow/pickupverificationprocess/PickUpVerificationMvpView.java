package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess;

import com.thresholdsoft.mpospicker.ui.base.MvpView;

public interface PickUpVerificationMvpView extends MvpView {
    void onItemClick(int position, PickUpVerificationActivity.PickPackProductsData pickPackProductsData);

    void onPartialWarningYesClick();

    void onPartialWarningNoClick();

    void onClickReVerificatio();

    void onClickVerification();

    void onBackClick();

    boolean recyclerItemClickableStatus();

}
