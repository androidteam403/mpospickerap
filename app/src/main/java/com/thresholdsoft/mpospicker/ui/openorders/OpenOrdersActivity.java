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
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.openorders.adapter.FullfilmentAdapter;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpActivity;

import java.util.ArrayList;
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
        getFullfilmentModelList();
        openOrdersBinding.headerOrdersCount.setText("Total " + fullfilmentModelList.size() + " orders");
        fullfilmentAdapter = new FullfilmentAdapter(this, fullfilmentModelList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        openOrdersBinding.fullfilmentRecycler.setLayoutManager(mLayoutManager);
        openOrdersBinding.fullfilmentRecycler.setAdapter(fullfilmentAdapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onFullfillmentItemClick(int pos) {
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
    public void onClickContinue() {
        if (isContinueEnable) {
            startActivity(ReadyForPickUpActivity.getStartActivity(this));
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        } else {
            Toast.makeText(this, "Selected orders.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getFullfilmentModelList() {
        fullfilmentModelList = new ArrayList<>();

        FullfilmentAdapter.FullfilmentModel fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(12);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("362548");
        fullfilmentModel.setTotalItems(22);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("265689");
        fullfilmentModel.setTotalItems(5);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);

        fullfilmentModel = new FullfilmentAdapter.FullfilmentModel();
        fullfilmentModel.setFullfilmentId("886677");
        fullfilmentModel.setTotalItems(20);
        fullfilmentModel.setSelected(false);
        fullfilmentModelList.add(fullfilmentModel);
    }
}
