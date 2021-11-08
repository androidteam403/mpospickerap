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
import com.thresholdsoft.mpospicker.databinding.DialogCustomUpdateStatusBinding;
import com.thresholdsoft.mpospicker.databinding.DialogUpdateStatusBinding;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationActivity;

public class UpdateStatusDialog {

    private Dialog dialog;
    private DialogCustomUpdateStatusBinding dialogUpdateStatusBinding;

    private boolean negativeExist = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public UpdateStatusDialog(Context context, PickUpVerificationActivity.PickPackProductsData pickPackProductsData) {
        dialog = new Dialog(context);
        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogUpdateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_update_status, null, false);
        dialog.setContentView(dialogUpdateStatusBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        if (pickPackProductsData != null) {
            dialogUpdateStatusBinding.title.setText(pickPackProductsData.getProduct());
        }
        if (pickPackProductsData.getProductStatus() == 0) {
            dialogUpdateStatusBinding.fullStatusColor.setChecked(true);
            dialogUpdateStatusBinding.fullStatusColor.setButtonTintList(ColorStateList.valueOf(context.getColor(R.color.black)));
            dialogUpdateStatusBinding.partialStatusColor.setChecked(false);
            dialogUpdateStatusBinding.notAvailableStatusColor.setChecked(false);
        } else if (pickPackProductsData.getProductStatus() == 1) {
            dialogUpdateStatusBinding.fullStatusColor.setChecked(false);
            dialogUpdateStatusBinding.partialStatusColor.setChecked(true);
            dialogUpdateStatusBinding.notAvailableStatusColor.setChecked(false);

        } else if (pickPackProductsData.getProductStatus() == 2) {
            dialogUpdateStatusBinding.fullStatusColor.setChecked(false);
            dialogUpdateStatusBinding.partialStatusColor.setChecked(false);
            dialogUpdateStatusBinding.notAvailableStatusColor.setChecked(true);
        }
    }


    public void setPositiveListener(View.OnClickListener okListener) {
        dialogUpdateStatusBinding.dialogButtonOK.setOnClickListener(okListener);
    }


    public void setNegativeListener(View.OnClickListener okListener) {
        dialogUpdateStatusBinding.dialogButtonNO.setOnClickListener(okListener);
    }

    public void show() {

        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setTitle(String title) {
        dialogUpdateStatusBinding.title.setText(title);
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
