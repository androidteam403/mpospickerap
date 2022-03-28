package com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivitySelectedOrderPickupProcessBinding;
import com.thresholdsoft.mpospicker.databinding.DialogItemStatusDropdownBinding;
import com.thresholdsoft.mpospicker.databinding.DialogUpdateStatusBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.batchlist.BatchListActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.adapter.SelectedPickupProcessProductsAdapter;

import javax.inject.Inject;

public class SelectedOrderPickupProcessActivity extends BaseActivity implements SelectedOrderPickupProcessMvpView {

    @Inject
    SelectedOrderPickupProcessMvpPresenter<SelectedOrderPickupProcessMvpView> mPresenter;
    private ActivitySelectedOrderPickupProcessBinding selectedOrderPickupProcessBinding;
    private DialogUpdateStatusBinding dialogUpdateStatusBinding;
    String[] statusSpinnerList = {"Partially Packed", "Fully Packed", "Not Available", "Cancelled"};

    public static Intent getStartIntent(Context mContext, RacksDataResponse.FullfillmentDetail fullfillmentDetail) {
        Intent intent = new Intent(mContext, SelectedOrderPickupProcessActivity.class);
        intent.putExtra("fullfillmentDetail", fullfillmentDetail);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedOrderPickupProcessBinding = DataBindingUtil.setContentView(this, R.layout.activity_selected_order_pickup_process);
        getActivityComponent().inject(this);
        mPresenter.onAttach(SelectedOrderPickupProcessActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        selectedOrderPickupProcessBinding.setCallback(mPresenter);
        mPresenter.onRackApiCall();
//        statusSpinner();
    }

    private String statusSpinnerSelected;

    private void statusSpinner() {

//        selectedOrderPickupProcessBinding.statusSpinner.getEditText().setTypeface(Typeface.createFromAsset(getAssets(), "font/roboto_regular.ttf"));
//        selectedOrderPickupProcessBinding.statusSpinner.setTypeface(Typeface.createFromAsset(getAssets(), "font/roboto_regular.ttf"));
//        android.R.layout.simple_spinner_item

        ArrayAdapter<String> genderSpinnerPojo = new ArrayAdapter<String>(this, R.layout.adapter_status_spinner, statusSpinnerList) {
            @NonNull
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "font/roboto_regular.ttf");
                ((TextView) v).setTypeface(externalFont);
                ((TextView) v).setTextColor(Color.BLACK);
                return v;
            }

            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "font/roboto_regular.ttf");
                ((TextView) v).setTypeface(externalFont);
                ((TextView) v).setTextColor(Color.BLACK);
                return v;
            }
        };
        genderSpinnerPojo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        selectedOrderPickupProcessBinding.statusSpinner.setAdapter(genderSpinnerPojo);
//        selectedOrderPickupProcessBinding.statusSpinner.setSelection(0);


//        StatusSpinnerAdapter customAdapter = new StatusSpinnerAdapter(this, statusSpinnerList, null);
//        selectedOrderPickupProcessBinding.statusSpinner.setAdapter(customAdapter);
//        selectedOrderPickupProcessBinding.statusSpinner.setSelection(0);
//
//        selectedOrderPickupProcessBinding.statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                SelectedOrderPickupProcessActivity.this.statusSpinnerSelected = statusSpinnerList[position];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    @Override
    public void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onRackApiCall() {

    }

    @Override
    public void onSuccessRackApi(RacksDataResponse racksDataResponse) {
        if (racksDataResponse != null && racksDataResponse.getFullfillmentDetails().get(0) != null) {
            SelectedPickupProcessProductsAdapter selectedPickupProcessProductsAdapter = new SelectedPickupProcessProductsAdapter(this, racksDataResponse.getFullfillmentDetails().get(0).getProducts(), this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            selectedOrderPickupProcessBinding.selectedPickupProcessProductList.setLayoutManager(mLayoutManager);
            selectedOrderPickupProcessBinding.selectedPickupProcessProductList.setAdapter(selectedPickupProcessProductsAdapter);
        }
    }

    @Override
    public void onClickStausIcon() {
        Dialog statusUpdateDialog = new Dialog(this, R.style.fadeinandoutcustomDialog);
        dialogUpdateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_update_status, null, false);
        dialogUpdateStatusBinding.setCallback(mPresenter);
        statusUpdateDialog.setContentView(dialogUpdateStatusBinding.getRoot());
        statusUpdateDialog.setCancelable(false);
        dialogUpdateStatusBinding.dismissDialog.setOnClickListener(view -> statusUpdateDialog.dismiss());
        statusUpdateDialog.show();
    }

    @Override
    public void onClickFullPicked() {
        checkAllFalse();
        dialogUpdateStatusBinding.fullPickedRadio.setChecked(true);
        dialogUpdateStatusBinding.fullDetails.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClickPartialPicked() {
        checkAllFalse();
        dialogUpdateStatusBinding.partiallyPickedRadio.setChecked(true);
        dialogUpdateStatusBinding.partialDetails.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClickNotAvailable() {
        checkAllFalse();
        dialogUpdateStatusBinding.notAvailableRadio.setChecked(true);
    }

    @Override
    public void onClickSkip() {
        checkAllFalse();
        dialogUpdateStatusBinding.skipRadioBtn.setChecked(true);
    }

    @Override
    public void onClickBatchDetails() {
        startActivity(BatchListActivity.getStartIntent(this));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void statusSpinnerCallback(int status) {

    }

    @Override
    public void onClickItemStatusDropdown() {
        BottomSheetDialog itemStatusDropdownDialog = new BottomSheetDialog(this);
        DialogItemStatusDropdownBinding itemStatusDropdownBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_item_status_dropdown, null, false);
        itemStatusDropdownDialog.setContentView(itemStatusDropdownBinding.getRoot());
        itemStatusDropdownDialog.show();
    }

    private void checkAllFalse() {
        dialogUpdateStatusBinding.fullPickedRadio.setChecked(false);
        dialogUpdateStatusBinding.partiallyPickedRadio.setChecked(false);
        dialogUpdateStatusBinding.notAvailableRadio.setChecked(false);
        dialogUpdateStatusBinding.skipRadioBtn.setChecked(false);

        dialogUpdateStatusBinding.fullDetails.setVisibility(View.GONE);
        dialogUpdateStatusBinding.partialDetails.setVisibility(View.GONE);

    }
}
