package com.thresholdsoft.mpospicker.ui.billerflow.orderdetailsscreen;

import com.thresholdsoft.mpospicker.ui.base.MvpPresenter;
import com.thresholdsoft.mpospicker.ui.base.MvpView;

public interface OrderDetailsScreenMvpPresenter <V extends OrderDetailsScreenMvpView> extends MvpPresenter<V> {

    void onMinusCustomerDetails();

    void onPlusCustomerDetails();

    void onminusOrderDetails();

    void onplusOrderDetails();

    void onminusVendorDetails();

    void onPlusVendorDetails();

    void onActionsContinue();

    void onGenerateBill();

    void onPrintLabel();

    void onPrintShippingLabel();

    void onSendBacktoPackerLabel();


}
