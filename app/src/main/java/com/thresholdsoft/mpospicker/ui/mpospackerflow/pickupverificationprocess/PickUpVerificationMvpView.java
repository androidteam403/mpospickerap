package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess;

import com.thresholdsoft.mpospicker.ui.base.MvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;

public interface PickUpVerificationMvpView extends MvpView {
    void onItemClick(int position, RackAdapter.RackBoxModel.ProductData pickPackProductsData);

    void onPartialWarningYesClick();

    void onPartialWarningNoClick();

    void onClickReVerificatio();

    void onClickVerification();

    boolean recyclerItemClickableStatus();
}
