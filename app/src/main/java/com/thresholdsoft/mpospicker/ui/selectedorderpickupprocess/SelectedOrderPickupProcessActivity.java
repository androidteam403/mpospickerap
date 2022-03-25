package com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivitySelectedOrderPickupProcessBinding;
import com.thresholdsoft.mpospicker.databinding.DialogUpdateStatusBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.adapter.SelectedPickupProcessProductsAdapter;

import javax.inject.Inject;

public class SelectedOrderPickupProcessActivity extends BaseActivity implements SelectedOrderPickupProcessMvpView {

    String[] names={"Partially Filled","Fully Filled","Not Available"};
    private ActivitySelectedOrderPickupProcessBinding selectedOrderPickupProcessBinding;
    @Inject
    SelectedOrderPickupProcessMvpPresenter<SelectedOrderPickupProcessMvpView> mPresenter;

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

//        ArrayAdapter<String>  adapter=new ArrayAdapter<String>(SelectedOrderPickupProcessActivity.this, R.layout.itemlist,names);
//                adapter.setDropDownViewResource(R.layout.itemlist);
//        selectedOrderPickupProcessBinding.spinner.setAdapter(adapter);
//
//        selectedOrderPickupProcessBinding.spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String value=adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(SelectedOrderPickupProcessActivity.this,value,Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected void setUp() {
        selectedOrderPickupProcessBinding.setCallback(mPresenter);
        mPresenter.onRackApiCall();
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
        DialogUpdateStatusBinding dialogUpdateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_update_status, null, false);
        statusUpdateDialog.setContentView(dialogUpdateStatusBinding.getRoot());
        statusUpdateDialog.setCancelable(false);
        dialogUpdateStatusBinding.dismissDialog.setOnClickListener(view -> statusUpdateDialog.dismiss());
        statusUpdateDialog.show();
    }
}
