package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityPickupVerificationBinding;
import com.thresholdsoft.mpospicker.databinding.DialogCustomUpdateStatusBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.adapter.PickUpVerificationAdapter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.dialog.VerificationStatusDialog;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class PickUpVerificationActivity extends BaseActivity implements PickUpVerificationMvpView {

    @Inject
    PickUpVerificationMvpPresenter<PickUpVerificationMvpView> mpresenter;
    private ActivityPickupVerificationBinding activityPickupVerificationBinding;
    private PickUpVerificationAdapter pickUpVerificationAdapter;
    private boolean updateVerified;
    boolean reverification;
    List<RackAdapter.RackBoxModel.ProductData> productDataList;
    private String status;
    private int position;
    List<RacksDataResponse.FullfillmentDetail> fullFillModelList;

    RacksDataResponse.FullfillmentDetail fillModel;


    public static Intent getStartActivity(Context context, int position, String status, String productDataList, String fullFillModelList, RacksDataResponse.FullfillmentDetail fillModel) {
        Intent intent = new Intent(context, PickUpVerificationActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("status", status);
        intent.putExtra("productDataList", productDataList);
        intent.putExtra("fullFillModelList", fullFillModelList);
        intent.putExtra("fillModel", (Serializable) fillModel);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPickupVerificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_pickup_verification);
        getActivityComponent().inject(this);
        mpresenter.onAttach(PickUpVerificationActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        activityPickupVerificationBinding.setCallback(mpresenter);
        if (getIntent() != null) {
            position = (int) getIntent().getIntExtra("position", 0);
            status = (String) getIntent().getStringExtra("status");
            fillModel = (RacksDataResponse.FullfillmentDetail) getIntent().getSerializableExtra("fillModel");

            Gson gson = new Gson();
            String json = getIntent().getStringExtra("productDataList");
            Type type = new TypeToken<List<RackAdapter.RackBoxModel.ProductData>>() {
            }.getType();
            if (productDataList != null) {
                productDataList.clear();
            }
            productDataList = gson.fromJson(json, type);


            Gson gson1 = new Gson();
            String json1 = getIntent().getStringExtra("fullFillModelList");
            Type type1 = new TypeToken<List<RacksDataResponse.FullfillmentDetail>>() {
            }.getType();
            if (fullFillModelList != null) {
                fullFillModelList.clear();
            }
            fullFillModelList = gson1.fromJson(json1, type1);

        }

        if (fillModel != null) {
            activityPickupVerificationBinding.fullfilmentId.setText(fillModel.getFullfillmentId());
            activityPickupVerificationBinding.orderId.setText(fillModel.getFullfillmentId());
            activityPickupVerificationBinding.boxId.setText(fillModel.getBoxId());
        }

        for (RackAdapter.RackBoxModel.ProductData productData : productDataList) {
            if (productData.getPackerStatus() != null && !productData.getPackerStatus().equalsIgnoreCase("")) {
                activityPickupVerificationBinding.sendReVer.setBackgroundResource(R.color.red);
                activityPickupVerificationBinding.sendReVer.setTextColor(getResources().getColor(R.color.white));
                activityPickupVerificationBinding.pickVerified.setBackgroundResource(R.color.yellow);
                activityPickupVerificationBinding.pickVerified.setTextColor(getResources().getColor(R.color.black));
                updateVerified = true;
            }
        }

        pickUpVerificationAdapter = new PickUpVerificationAdapter(this, productDataList, this);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityPickupVerificationBinding.recyclerView.setLayoutManager(mLayoutManager1);
        activityPickupVerificationBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityPickupVerificationBinding.recyclerView.setAdapter(pickUpVerificationAdapter);

        if (status.equalsIgnoreCase("Partial")) {
            activityPickupVerificationBinding.parent.setBackgroundColor(getResources().getColor(R.color.white));
            activityPickupVerificationBinding.parent.setAlpha((float) 0.4);
            activityPickupVerificationBinding.buttonParent.setAlpha((float) 0.4);
            activityPickupVerificationBinding.statusText.setText("Partial");
            activityPickupVerificationBinding.partialStatusColor.setVisibility(View.VISIBLE);
            activityPickupVerificationBinding.warningText.setVisibility(View.VISIBLE);
            partialConfirmationClickable = false;
        } else {
            activityPickupVerificationBinding.parent.setBackground(null);
            activityPickupVerificationBinding.buttonParent.setBackground(null);
            activityPickupVerificationBinding.statusText.setText("Full");
            activityPickupVerificationBinding.partialStatusColor.setVisibility(View.GONE);
            activityPickupVerificationBinding.warningText.setVisibility(View.GONE);
            partialConfirmationClickable = true;
        }

        activityPickupVerificationBinding.backClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    int flag;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onItemClick(int position, RackAdapter.RackBoxModel.ProductData pickPackProductsData) {
        Dialog dialog = new Dialog(this, R.style.fadeinandoutcustomDialog);

        DialogCustomUpdateStatusBinding dialogUpdateStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.dialog_custom_update_status, null, false);
        dialog.setContentView(dialogUpdateStatusBinding.getRoot());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        if (pickPackProductsData != null) {
            dialogUpdateStatusBinding.title.setText(pickPackProductsData.getProductName());
            dialogUpdateStatusBinding.qty.setText(pickPackProductsData.getCapturedQuantity());
        }
        dialogUpdateStatusBinding.id.setText(fillModel.getFullfillmentId());
        dialogUpdateStatusBinding.boxId.setText(fillModel.getBoxId());
        dialogUpdateStatusBinding.fullStatusColorLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dialogUpdateStatusBinding.fullStatusColor.isChecked()) {
                    dialogUpdateStatusBinding.fullStatusColor.setChecked(true);
                    dialogUpdateStatusBinding.fullStatusColor.setButtonTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.black)));
                    dialogUpdateStatusBinding.partialStatusColor.setChecked(false);
                    dialogUpdateStatusBinding.notAvailableStatusColor.setChecked(false);
                    pickPackProductsData.setPackerStatus("Full");
                }
            }
        });

        dialogUpdateStatusBinding.fullStatusColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogUpdateStatusBinding.fullStatusColor.isChecked()) {
                    dialogUpdateStatusBinding.fullStatusColor.setChecked(true);
                    dialogUpdateStatusBinding.fullStatusColor.setButtonTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.black)));
                    dialogUpdateStatusBinding.partialStatusColor.setChecked(false);
                    dialogUpdateStatusBinding.notAvailableStatusColor.setChecked(false);
                    pickPackProductsData.setPackerStatus("Full");
                }
            }
        });

        dialogUpdateStatusBinding.partialStatusColorLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dialogUpdateStatusBinding.partialStatusColor.isChecked()) {
                    dialogUpdateStatusBinding.fullStatusColor.setChecked(false);
                    dialogUpdateStatusBinding.partialStatusColor.setChecked(true);
                    dialogUpdateStatusBinding.partialStatusColor.setButtonTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.black)));
                    dialogUpdateStatusBinding.notAvailableStatusColor.setChecked(false);
                    pickPackProductsData.setPackerStatus("Partial");
                }
            }
        });

          dialogUpdateStatusBinding.partialStatusColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogUpdateStatusBinding.partialStatusColor.isChecked()) {
                    dialogUpdateStatusBinding.fullStatusColor.setChecked(false);
                    dialogUpdateStatusBinding.partialStatusColor.setChecked(true);
                    dialogUpdateStatusBinding.partialStatusColor.setButtonTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.black)));
                    dialogUpdateStatusBinding.notAvailableStatusColor.setChecked(false);
                    pickPackProductsData.setPackerStatus("Partial");
                }
            }
        });
        dialogUpdateStatusBinding.notAvailableStatusColorLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dialogUpdateStatusBinding.notAvailableStatusColor.isChecked()) {
                    dialogUpdateStatusBinding.fullStatusColor.setChecked(false);
                    dialogUpdateStatusBinding.partialStatusColor.setChecked(false);
                    dialogUpdateStatusBinding.notAvailableStatusColor.setChecked(true);
                    dialogUpdateStatusBinding.notAvailableStatusColor.setButtonTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.black)));
                    pickPackProductsData.setPackerStatus("Not Available");
                }
            }
        });

         dialogUpdateStatusBinding.notAvailableStatusColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogUpdateStatusBinding.notAvailableStatusColor.isChecked()) {
                    dialogUpdateStatusBinding.fullStatusColor.setChecked(false);
                    dialogUpdateStatusBinding.partialStatusColor.setChecked(false);
                    dialogUpdateStatusBinding.notAvailableStatusColor.setChecked(true);
                    dialogUpdateStatusBinding.notAvailableStatusColor.setButtonTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.black)));
                    pickPackProductsData.setPackerStatus("Not Available");
                }
            }
        });

        dialogUpdateStatusBinding.dialogButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogUpdateStatusBinding.fullStatusColor.isChecked() || dialogUpdateStatusBinding.partialStatusColor.isChecked() || dialogUpdateStatusBinding.notAvailableStatusColor.isChecked()) {
                    activityPickupVerificationBinding.sendReVer.setBackgroundResource(R.color.red);
                    activityPickupVerificationBinding.sendReVer.setTextColor(getResources().getColor(R.color.white));
                    activityPickupVerificationBinding.pickVerified.setBackgroundResource(R.color.yellow);
                    activityPickupVerificationBinding.pickVerified.setTextColor(getResources().getColor(R.color.black));
                    updateVerified = true;
//                pickUpVerificationAdapter.notifyDataSetChanged();
                    productDataList.get(position).setPackerStatus(pickPackProductsData.getPackerStatus());
                    pickUpVerificationAdapter = new PickUpVerificationAdapter(PickUpVerificationActivity.this, productDataList, PickUpVerificationActivity.this);
                    RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(PickUpVerificationActivity.this, LinearLayoutManager.VERTICAL, false);
                    activityPickupVerificationBinding.recyclerView.setLayoutManager(mLayoutManager1);
                    activityPickupVerificationBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
                    activityPickupVerificationBinding.recyclerView.setAdapter(pickUpVerificationAdapter);
                    dialog.dismiss();
                } else {
                    Toast.makeText(PickUpVerificationActivity.this, "Please Update status", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogUpdateStatusBinding.dialogButtonNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @SuppressLint("Range")
    @Override
    public void onPartialWarningYesClick() {
        partialConfirmationClickable = true;
        activityPickupVerificationBinding.statusText.setText("Partial");
        activityPickupVerificationBinding.partialStatusColor.setVisibility(View.VISIBLE);
        activityPickupVerificationBinding.warningText.setVisibility(View.GONE);
        activityPickupVerificationBinding.parent.setBackgroundColor(0);
        activityPickupVerificationBinding.buttonParent.setBackgroundColor(0);
        activityPickupVerificationBinding.parent.setAlpha(4);
        activityPickupVerificationBinding.buttonParent.setAlpha(4);
        activityPickupVerificationBinding.parent.setClickable(true);
    }

    @Override
    public void onPartialWarningNoClick() {
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClickReVerificatio() {
        if (updateVerified && partialConfirmationClickable) {
            reverification = true;
            activityPickupVerificationBinding.sendReVer.setClickable(true);
            VerificationStatusDialog verificationStatusDialog = new VerificationStatusDialog(PickUpVerificationActivity.this, reverification, fillModel.getFullfillmentId());
            verificationStatusDialog.setPositiveListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    verificationStatusDialog.dismiss();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
            });
            verificationStatusDialog.setNegativeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verificationStatusDialog.dismiss();
                }
            });

            verificationStatusDialog.show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClickVerification() {
        if (updateVerified && partialConfirmationClickable) {
            reverification = false;
            activityPickupVerificationBinding.pickVerified.setClickable(true);
            VerificationStatusDialog verificationStatusDialog = new VerificationStatusDialog(PickUpVerificationActivity.this, reverification, fillModel.getFullfillmentId());
            verificationStatusDialog.setPositiveListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    Gson gson = new Gson();
                    String myJson = gson.toJson(productDataList);
                    intent.putExtra("productDataList", myJson);
                    intent.putExtra("position", position);
                    Gson gson1 = new Gson();
                    String myJson1 = gson1.toJson(fullFillModelList);
                    intent.putExtra("fullFillModelList", myJson1);
                    setResult(RESULT_OK, intent);
                    finish();
                    verificationStatusDialog.dismiss();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
            });
            verificationStatusDialog.setNegativeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    verificationStatusDialog.dismiss();
                }
            });

            verificationStatusDialog.show();
        }
    }

    private boolean partialConfirmationClickable;

    @Override
    public boolean recyclerItemClickableStatus() {
        return partialConfirmationClickable;
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
