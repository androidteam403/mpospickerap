package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders;

import com.thresholdsoft.mpospicker.ui.base.MvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public interface PickedUpOrdersMvpView extends MvpView {
    void startPickUp();
    void onClickScanCode();
    void onItemClick(int position, String status, List<RackAdapter.RackBoxModel.ProductData> productDataList, List<RacksDataResponse.FullfillmentDetail> fullFillModel, RacksDataResponse.FullfillmentDetail fillModel);
}
