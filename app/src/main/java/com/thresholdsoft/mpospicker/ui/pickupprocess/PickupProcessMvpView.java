package com.thresholdsoft.mpospicker.ui.pickupprocess;

import android.widget.Spinner;

import com.thresholdsoft.mpospicker.ui.base.MvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.OrderAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public interface PickupProcessMvpView extends MvpView {

    void onClickBack();

    void onClickContinue();



    void onClickFullPicked();
    void onClickStausIcon();

    void onClickBatchDetails();

    void onClickPartialPicked();

    void onClickNotAvailable();

    void onClickSkip();


    void onClickDropDown(Spinner spinner);

    void onSuccessRackApi(RacksDataResponse racksDataResponse);

    void onRackStatusUpdate(List<List<RackAdapter.RackBoxModel.ProductData>> listOfList);

    void onFullfillmentOrderStatusUpdate(List<List<OrderAdapter.RackBoxModel.ProductData>> listOfList);

    List<List<RackAdapter.RackBoxModel.ProductData>> productList();

    List<List<OrderAdapter.RackBoxModel.ProductData>> fullfilListOfList();

    List<RackAdapter.RackBoxModel.ProductData> productsNextPosReturn(List<RackAdapter.RackBoxModel.ProductData> productsNextPosReturn);

    List<RackAdapter.RackBoxModel.ProductData> productsNextPosReturn();

    void onClickRightArrow(RacksDataResponse.FullfillmentDetail fullfillmentDetail);

}
