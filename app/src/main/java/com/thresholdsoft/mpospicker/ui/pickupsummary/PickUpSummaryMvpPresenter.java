package com.thresholdsoft.mpospicker.ui.pickupsummary;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public interface PickUpSummaryMvpPresenter<V extends PickUpSummaryMvpView> extends MvpPresenter<V> {
    void forwardtoPacker();

    void setFullfillmentData(List<RacksDataResponse.FullfillmentDetail> fullfillmentDetailList);

    List<RacksDataResponse.FullfillmentDetail> getFullFillmentList();

    void setListOfListFullfillmentData(List<List<RackAdapter.RackBoxModel.ProductData>> listOfListFullfillmentDetailList);

    List<List<RackAdapter.RackBoxModel.ProductData>> getListOfListFullFillmentList();
}
