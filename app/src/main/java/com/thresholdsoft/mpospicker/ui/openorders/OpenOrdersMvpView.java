package com.thresholdsoft.mpospicker.ui.openorders;

import com.thresholdsoft.mpospicker.ui.base.MvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

public interface OpenOrdersMvpView extends MvpView {
    void onFullfillmentItemClick(int pos);

    void onRightArrowClickedContinue(int pos);

    void onClickContinue();

    void onSuccessRackApi(RacksDataResponse body);

    void onClickFilterIcon();

    void onClickStausIcon(int fullFillmentPos, int pos);
}
