package com.thresholdsoft.mpospicker.ui.openorders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityOpenOrdersBinding;
import com.thresholdsoft.mpospicker.ui.DeciderScreen;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersActivity;
import com.thresholdsoft.mpospicker.ui.openorders.adapter.FullfilmentAdapter;
import com.thresholdsoft.mpospicker.ui.orderdetails.OrderDetailsActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class OpenOrdersActivity extends BaseActivity implements OpenOrdersMvpView {
    @Inject
    OpenOrdersMvpPresenter<OpenOrdersMvpView> mPresenter;
    private ActivityOpenOrdersBinding openOrdersBinding;
    private List<FullfilmentAdapter.FullfilmentModel> fullfilmentModelList;
    private FullfilmentAdapter fullfilmentAdapter;
    private boolean isContinueEnable;


    public static Intent getStartActivity(Context context) {
        return new Intent(context, OpenOrdersActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openOrdersBinding = DataBindingUtil.setContentView(this, R.layout.activity_open_orders);
        getActivityComponent().inject(this);
        mPresenter.onAttach(OpenOrdersActivity.this);
        setUp();


    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setUp() {
        openOrdersBinding.setCallback(mPresenter);
        Glide.with(this).load("https://apis.v35.dev.zeroco.de/zc-v3.1-fs-svc/2.0/apollo_rider/get/41B8F83052E720DA0FC28401C9BFAA90396DCB4FD14F508D641DBC42F5808C634160E6E9BDFF4D97E46A107F1185330BE9BE56FEC6E2C512EC7E08CAAA498D8FA633B599A9A34C9C97BCF338231C7AA91F16F94D257D61803FBC97DE5FEEACF62933C5F49DFFBE9EBADD5C68A6A9245EE277F7369BEBB4A75B56F81CDA296FE0F45824C81F0E7A9C29BA1E691D49C48BCB3E2586250A732BC0C95D8C9A1E1154C38FC1DFED04C09C36722BD70B9D0E10952C6B12C3EABEF551397B781F83118196C4F5899C1A7EBB728DE8B78537C55B735B4BEAE021E0391CB1ACE72296B00A8869B3AA7F4BF1674AC2BF9952BF39A67ABCA6DC6BF69C69CCC9C5766F79B2F9").circleCrop().into(openOrdersBinding.pickerImg);
//        getFullfilmentModelList();
        mPresenter.onRackApiCall();
    }

    private RacksDataResponse racksDataResponse;

    @Override
    public void onSuccessRackApi(RacksDataResponse racksDataResponse) {
        this.racksDataResponse = racksDataResponse;
        if (racksDataResponse != null && racksDataResponse.getFullfillmentDetails() != null && racksDataResponse.getFullfillmentDetails().size() > 0) {
            fullfilmentModelList = new ArrayList<>();
            for (int i = 0; i < racksDataResponse.getFullfillmentDetails().size(); i++) {
                FullfilmentAdapter.FullfilmentModel fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
                fullfilmentModel.setFullfilmentId(racksDataResponse.getFullfillmentDetails().get(i).getFullfillmentId());
                fullfilmentModel.setTotalItems(racksDataResponse.getFullfillmentDetails().get(i).getTotalItems());
                fullfilmentModel.setSelected(false);
                fullfilmentModelList.add(fullfilmentModel);
            }

            openOrdersBinding.headerOrdersCount.setText("Total " + fullfilmentModelList.size() + " orders");
            fullfilmentAdapter = new FullfilmentAdapter(this, fullfilmentModelList, this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            openOrdersBinding.fullfilmentRecycler.setLayoutManager(mLayoutManager);
            openOrdersBinding.fullfilmentRecycler.setAdapter(fullfilmentAdapter);
        }
    }

    int pos;

    @SuppressLint("SetTextI18n")
    @Override
    public void onFullfillmentItemClick(int pos) {
        this.pos = pos;

//
////
        if (fullfilmentModelList != null && fullfilmentModelList.size() > 0) {
            int selectedCount = 0;
            for (FullfilmentAdapter.FullfilmentModel fullfilmentModel : fullfilmentModelList) {
                if (fullfilmentModel.isSelected()) {
                    selectedCount++;
                }
            }
            if (selectedCount < 5) {
                fullfilmentModelList.get(pos).setSelected(!fullfilmentModelList.get(pos).isSelected());
            } else {
                if (fullfilmentModelList.get(pos).isSelected())
                    fullfilmentModelList.get(pos).setSelected(false);
            }
            if (fullfilmentAdapter != null)
                fullfilmentAdapter.notifyDataSetChanged();
            boolean isAnyoneSelect = false;
            int selectedItemCount = 0;
            for (FullfilmentAdapter.FullfilmentModel fullfilmentModel : fullfilmentModelList)
                if (fullfilmentModel.isSelected()) {
                    isAnyoneSelect = true;
                    selectedItemCount++;
                }
            this.isContinueEnable = isAnyoneSelect;
            if (isAnyoneSelect) {
                openOrdersBinding.selectedFullfillment.setText("Selected fullfillment " + selectedItemCount + "/5.");
                openOrdersBinding.continueBtn.setBackgroundColor(getResources().getColor(R.color.continue_select_color));
                openOrdersBinding.setIsContinueSelect(true);
            } else {
                openOrdersBinding.selectedFullfillment.setText("Select fullfilment to start pichup process.");
                openOrdersBinding.continueBtn.setBackgroundColor(getResources().getColor(R.color.continue_unselect_color));
                openOrdersBinding.setIsContinueSelect(false);
            }
            openOrdersBinding.selectedItemCount.setText(selectedItemCount + "/5");
     }
   }

    @Override
    protected void onResume() {
        super.onResume();
        selectedRacksDataResponse = new ArrayList<>();
    }

    private List<RacksDataResponse.FullfillmentDetail> selectedRacksDataResponse;

    @Override
    public void onClickContinue() {
        if (isContinueEnable) {

            for (int i = 0; i < fullfilmentModelList.size(); i++) {
                if (fullfilmentModelList.get(i).isSelected()) {
                    racksDataResponse.getFullfillmentDetails().get(i).setSelectedBoxesData(fullfilmentModelList.get(i).isSelected());
                } else {
                    racksDataResponse.getFullfillmentDetails().get(i).setSelectedBoxesData(fullfilmentModelList.get(i).isSelected());
                }
            }

            for (int i = 0; i < racksDataResponse.getFullfillmentDetails().size(); i++) {
                if (racksDataResponse.getFullfillmentDetails().get(i).isSelectedBoxesData()) {
                    selectedRacksDataResponse.add(racksDataResponse.getFullfillmentDetails().get(i));
                }
            }

            startActivity(ReadyForPickUpActivity.getStartActivity(this, selectedRacksDataResponse));
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        } else {
            Toast.makeText(this, "Please Select Orders", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRightArrowClickedContinue(int position) {
        if (racksDataResponse.getFullfillmentDetails() != null && racksDataResponse.getFullfillmentDetails().size() > 0 && racksDataResponse.getFullfillmentDetails().size() > pos) {
//
            Intent i = new Intent(OpenOrdersActivity.this, OrderDetailsActivity.class);
            i.putExtra("fullfillmentDetails", racksDataResponse.getFullfillmentDetails().get(position));
            startActivityForResult(i, 999);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(DeciderScreen.getStartActivity(OpenOrdersActivity.this));
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }




    boolean isAnyoneSelect = false;
    int selectedItemCount=0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {


             RacksDataResponse.FullfillmentDetail fullfillmentIdNew = (RacksDataResponse.FullfillmentDetail) data.getSerializableExtra("FullfillmentID");
             boolean isSelect = (Boolean) data.getSerializableExtra("isSelect");
                if(fullfillmentIdNew != null) {
                    for (int i = 0; i < fullfilmentModelList.size(); i++) {
                        if (fullfillmentIdNew.getFullfillmentId().equals(fullfilmentModelList.get(i).getFullfilmentId())) {
                            fullfilmentModelList.get(i).setSelected(isSelect);
                            if (selectedItemCount>fullfilmentModelList.size()){
                              selectedItemCount=fullfilmentModelList.size();
                            }else if(!fullfilmentModelList.get(i).isSelected()){
                                selectedItemCount++;
                            }
                            isAnyoneSelect = true;
                            fullfilmentAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                    if (fullfilmentAdapter != null)
                        fullfilmentAdapter.notifyDataSetChanged();
                    if (isAnyoneSelect) {
                        openOrdersBinding.selectedFullfillment.setText("Selected fullfillment " + selectedItemCount + "/5.");
                        openOrdersBinding.continueBtn.setBackgroundColor(getResources().getColor(R.color.continue_select_color));
                        openOrdersBinding.setIsContinueSelect(true);
                    } else {
                        openOrdersBinding.selectedFullfillment.setText("Select fullfilment to start pichup process.");
                        openOrdersBinding.continueBtn.setBackgroundColor(getResources().getColor(R.color.continue_unselect_color));
                        openOrdersBinding.setIsContinueSelect(false);
                    }
                    openOrdersBinding.selectedItemCount.setText(selectedItemCount + "/5");
                }

        }


    }
}

