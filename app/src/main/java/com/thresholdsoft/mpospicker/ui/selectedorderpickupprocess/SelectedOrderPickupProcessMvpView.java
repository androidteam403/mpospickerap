package com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess;

import com.thresholdsoft.mpospicker.ui.base.MvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

public interface SelectedOrderPickupProcessMvpView extends MvpView {
    void onClickBack();

    void onRackApiCall();

    void onSuccessRackApi(RacksDataResponse racksDataResponse);

    void onClickStausIcon();

    void onClickFullPicked();

    void onClickPartialPicked();

    void onClickNotAvailable();

    void onClickSkip();

    void onClickBatchDetails();

    void statusSpinnerCallback(int status);

    void onClickItemStatusDropdown();
}
