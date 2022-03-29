package com.thresholdsoft.mpospicker.ui.readyforpickup;

import com.thresholdsoft.mpospicker.ui.base.MvpView;

public interface ReadyForPickUpMvpView extends MvpView {
    void onTagBoxClick(String fullfillmentId, int position);

    void onDeleteClick(int position, String fullfillmentId);

    void onClickStartPickup();

    void onClickBack();

    void onClickStartPickingWithoutQrCode();

    void onTakePrintClick(int position);
}
