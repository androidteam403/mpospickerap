package com.thresholdsoft.mpospicker.ui.readyforpickup.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.DialogUntagQrcodeBinding;

public class UnTagQrCodeDialog {
    private Dialog dialog;
    private DialogUntagQrcodeBinding dialogUntagQrcodeBinding;

    private boolean negativeExist = false;

    public UnTagQrCodeDialog(Context context, String id) {
        dialog = new Dialog(context);
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogUntagQrcodeBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_untag_qrcode, null, false);
        dialog.setContentView(dialogUntagQrcodeBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        dialogUntagQrcodeBinding.title.setText(id);
    }


    public void setPositiveListener(View.OnClickListener okListener) {
        dialogUntagQrcodeBinding.dialogButtonOK.setOnClickListener(okListener);
    }


    public void setNegativeListener(View.OnClickListener okListener) {
        dialogUntagQrcodeBinding.dialogButtonNO.setOnClickListener(okListener);
        dialogUntagQrcodeBinding.dialogButtonNot.setOnClickListener(okListener);
    }

    public void show() {

        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setTitle(String title) {
        dialogUntagQrcodeBinding.title.setText(title);
    }


//    public void setPositiveLabel(String positive) {
//        alertDialogBoxBinding.btnYes.setText(positive);
//    }
//
//    public void setNegativeLabel(String negative) {
//        negativeExist = true;
//        alertDialogBoxBinding.btnNo.setText(negative);
//    }


}
