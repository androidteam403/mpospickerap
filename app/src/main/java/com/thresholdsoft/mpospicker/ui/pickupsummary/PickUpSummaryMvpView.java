package com.thresholdsoft.mpospicker.ui.pickupsummary;

import com.thresholdsoft.mpospicker.ui.base.MvpView;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.OrderAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;

import java.util.List;

public interface PickUpSummaryMvpView extends MvpView {
    void forwardtoPacker();

    List<List<OrderAdapter.RackBoxModel.ProductData>> fullfilListOfList();

    List<List<RackAdapter.RackBoxModel.ProductData>> productList();

    String fullCount(String fullCount);

    String partialCount(String partialCount);

    String notAvailable(String notAvailableCount);
}
