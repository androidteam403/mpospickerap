package com.thresholdsoft.mpospicker.ui.readyforpickup.scanner;

import java.util.List;

public interface CaptureManagerCallback {
    void scannedListener(List<String> barcodeList);
}
