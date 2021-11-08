package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.DialogVerificationStatusBinding;

public class VerificationStatusDialog {

    private Dialog dialog;
    private DialogVerificationStatusBinding verificationStatusBinding;

    private boolean negativeExist = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public VerificationStatusDialog(Context context, boolean reverification) {
        dialog = new Dialog(context);
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        verificationStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_verification_status, null, false);
        dialog.setContentView(verificationStatusBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        if (reverification) {
            verificationStatusBinding.title.setText("Push to Re-verification");
            verificationStatusBinding.statusImage.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.red)));
            verificationStatusBinding.statusImage.setImageResource(R.drawable.delete_white_icon);
        }
    }


    public void setPositiveListener(View.OnClickListener okListener) {
        verificationStatusBinding.dialogButtonOK.setOnClickListener(okListener);
    }


    public void setNegativeListener(View.OnClickListener okListener) {
        verificationStatusBinding.dialogButtonNO.setOnClickListener(okListener);
        verificationStatusBinding.dialogButtonNot.setOnClickListener(okListener);
    }

    public void show() {

        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setTitle(String title) {
        verificationStatusBinding.title.setText(title);
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
