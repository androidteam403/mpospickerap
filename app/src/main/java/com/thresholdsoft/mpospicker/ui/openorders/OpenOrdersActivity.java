package com.thresholdsoft.mpospicker.ui.openorders;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityOpenOrdersBinding;
import com.thresholdsoft.mpospicker.databinding.DialogFilterBinding;
import com.thresholdsoft.mpospicker.databinding.DialogUpdateStatusBinding;
import com.thresholdsoft.mpospicker.ui.DeciderScreen;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;

import com.thresholdsoft.mpospicker.ui.openorders.adapter.FullfilmentAdapter;
import com.thresholdsoft.mpospicker.ui.orderdetails.OrderDetailsActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.OrderAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpActivity;
import com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess.SelectedOrderPickupProcessActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class OpenOrdersActivity extends BaseActivity implements OpenOrdersMvpView {
    @Inject
    OpenOrdersMvpPresenter<OpenOrdersMvpView> mPresenter;
    private ActivityOpenOrdersBinding openOrdersBinding;
    private List<FullfilmentAdapter.FullfilmentModel> fullfilmentModelList;
    private FullfilmentAdapter fullfilmentAdapter;
    public List<RackAdapter.RackBoxModel.ProductData> productDataList;

    private boolean isContinueEnable;
//    private AppBarConfiguration mAppBarConfiguration;




    public static Intent getStartActivity(Context context) {
        return new Intent(context, OpenOrdersActivity.class);
    }



//    public static Intent getStartActivity(DashboardFragment dashboardFragment) {
//        return new Intent(dashboardFragment, OpenOrdersActivity.class);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openOrdersBinding = DataBindingUtil.setContentView(this, R.layout.activity_open_orders);
        getActivityComponent().inject(this);
        mPresenter.onAttach(OpenOrdersActivity.this);
        setUp();
        if (getIntent() != null) {
            productDataList = (List<RackAdapter.RackBoxModel.ProductData>) getIntent().getSerializableExtra("productDataList");
        }


    }

    @Override
    protected void setUp() {
        openOrdersBinding.setCallback(mPresenter);
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
            fullfilmentAdapter = new FullfilmentAdapter(this, fullfilmentModelList, this, productDataList, racksDataResponse);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            openOrdersBinding.fullfilmentRecycler.setLayoutManager(mLayoutManager);
            openOrdersBinding.fullfilmentRecycler.setAdapter(fullfilmentAdapter);
        }
    }

    @Override
    public void onClickFilterIcon() {
        Dialog filterDialog = new Dialog(this, R.style.fadeinandoutcustomDialog);
        DialogFilterBinding dialogFilterBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_filter, null, false);
        filterDialog.setContentView(dialogFilterBinding.getRoot());
        filterDialog.setCancelable(false);
        dialogFilterBinding.filterCloseIcon.setOnClickListener(view -> filterDialog.dismiss());
        filterDialog.show();
    }

    @Override
    public void onClickStausIcon(int fullFillmentPos, int pos) {
        Dialog statusUpdateDialog = new Dialog(this, R.style.fadeinandoutcustomDialog);
        DialogUpdateStatusBinding dialogUpdateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_update_status, null, false);
        statusUpdateDialog.setContentView(dialogUpdateStatusBinding.getRoot());
        statusUpdateDialog.setCancelable(false);
        dialogUpdateStatusBinding.dismissDialog.setOnClickListener(view -> statusUpdateDialog.dismiss());

        racksDataResponse.getFullfillmentDetails().get(0).getProducts().get(0).getItemStatus();

        statusUpdateDialog.show();
    }


    int pos;

    @SuppressLint("SetTextI18n")
    @Override
    public void onFullfillmentItemClick(int pos) {
        this.pos = pos;
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


            //            Intent i = new Intent(OpenOrdersActivity.this, OrderDetailsActivity.class);
//            i.putExtra("fullfillmentDetails", racksDataResponse.getFullfillmentDetails().get(position));
//            startActivityForResult(i, 999);
//            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

//            openOrdersBinding.layoutFulfilment.setVisibility(View.VISIBLE);
//            FulfimentDetailsAdapter fulfimentDetailsAdapter=new FulfimentDetailsAdapter(productDataList,this);
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//            openOrdersBinding.rackRecycler.setLayoutManager(mLayoutManager);
//            openOrdersBinding.rackRecycler.setAdapter(fulfimentDetailsAdapter);



        }
    }

    @Override
    public void onBackPressed() {
        startActivity(DeciderScreen.getStartActivity(OpenOrdersActivity.this));
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }


    int gotId;
    boolean isAnyoneSelect = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            RacksDataResponse.FullfillmentDetail fullfillmentIdNew = (RacksDataResponse.FullfillmentDetail) data.getSerializableExtra("FullfillmentID");
            boolean isSelect = (Boolean) data.getSerializableExtra("isSelect");
            if (fullfillmentIdNew != null) {
                for (int i = 0; i < fullfilmentModelList.size(); i++) {
                    if (fullfillmentIdNew.getFullfillmentId().equals(fullfilmentModelList.get(i).getFullfilmentId())) {
                        fullfilmentModelList.get(i).setSelected(isSelect);
                        fullfilmentAdapter.notifyDataSetChanged();
                        break;
                    }
                }
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
    }


//    @Override
//    public void onBackPressed() {
//        startActivity(DeciderScreen.getStartActivity(OpenOrdersActivity.this));
//        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
//    }
}
