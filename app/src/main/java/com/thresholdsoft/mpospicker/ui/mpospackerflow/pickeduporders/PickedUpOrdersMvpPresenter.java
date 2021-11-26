package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public interface PickedUpOrdersMvpPresenter<V extends PickedUpOrdersMvpView> extends MvpPresenter<V> {
    void startPickUp();

    void onClickScanCode();

    List<RacksDataResponse.FullfillmentDetail> getFullFillmentList();

    List<List<RackAdapter.RackBoxModel.ProductData>> getListOfListFullFillmentList();

    void setFullFillmentDataList(List<RacksDataResponse.FullfillmentDetail> fullFillmentDataList);

    void setListOfListFullFillProducts(List<List<RackAdapter.RackBoxModel.ProductData>> listOfListFullFillProducts);

}
