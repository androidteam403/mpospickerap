package com.thresholdsoft.mpospicker.ui.billerflow.billerOrdersScreen;

import com.thresholdsoft.mpospicker.ui.base.MvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

public interface BillerOrdersMvpView extends MvpView {


    void onclickScanCode();

    void onRightArrowClickedContinue(int position);

//    void onSuccessRackApi(RacksDataResponse body);

    void onClickFilterIcon();
}
