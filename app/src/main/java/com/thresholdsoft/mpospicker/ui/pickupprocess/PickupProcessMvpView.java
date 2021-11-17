package com.thresholdsoft.mpospicker.ui.pickupprocess;

import com.thresholdsoft.mpospicker.ui.base.MvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.OrderAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public interface PickupProcessMvpView extends MvpView {

    void onClickBack();

    void onClickContinue();

    void onSuccessRackApi(RacksDataResponse racksDataResponse);

    void onRackStatusUpdate(List<List<RackAdapter.RackBoxModel.ProductData>> listOfList);

    void onFullfillmentOrderStatusUpdate(List<List<OrderAdapter.RackBoxModel.ProductData>> listOfList);

    List<List<RackAdapter.RackBoxModel.ProductData>> productList();

    List<List<OrderAdapter.RackBoxModel.ProductData>> fullfilListOfList();


}
