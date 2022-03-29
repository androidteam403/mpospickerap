package com.thresholdsoft.mpospicker.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.thresholdsoft.mpospicker.R;


/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */
public class CommonUtils {
    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static SpannableString convertSpannableStrings(String header, String description, float headerSize, float descSize, int headerColor, int descColor) {
        SpannableString stringHeader = new SpannableString(header);
        stringHeader.setSpan(new RelativeSizeSpan(headerSize), 0, stringHeader.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringHeader.setSpan(new ForegroundColorSpan(headerColor), 0, stringHeader.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        SpannableString StringDesc = new SpannableString(description);
        StringDesc.setSpan(new RelativeSizeSpan(descSize), 0, StringDesc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        StringDesc.setSpan(new ForegroundColorSpan(descColor), 0, StringDesc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return new SpannableString(TextUtils.concat(stringHeader, "\n", StringDesc));
    }
}
