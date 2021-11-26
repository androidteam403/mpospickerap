package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityPickedUpOrdersBinding;
import com.thresholdsoft.mpospicker.ui.DeciderScreen;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.adapter.PickedUpOrdersAdapter;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickupverificationprocess.PickUpVerificationActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PickedUpOrdersActivity extends BaseActivity implements PickedUpOrdersMvpView {

    @Inject
    PickedUpOrdersMvpPresenter<PickedUpOrdersMvpView> mvpPresenter;
    private ActivityPickedUpOrdersBinding activityPickedUpOrdersBinding;
    private PickedUpOrdersAdapter pickedUpOrdersAdapter;
    private static final int PICKUP_VERIFICATION_ACTIVITY = 108;
    List<RackAdapter.RackBoxModel.ProductData> productDataList;
    List<RacksDataResponse.FullfillmentDetail> fullfillmentDetailList;

    public static Intent getStartActivity(Context context) {
        return new Intent(context, PickedUpOrdersActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPickedUpOrdersBinding = DataBindingUtil.setContentView(this, R.layout.activity_picked_up_orders);
        getActivityComponent().inject(this);
        mvpPresenter.onAttach(PickedUpOrdersActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        activityPickedUpOrdersBinding.setCallback(mvpPresenter);
        if (mvpPresenter.getFullFillmentList() != null) {
            activityPickedUpOrdersBinding.zeropicked.setVisibility(View.GONE);
            activityPickedUpOrdersBinding.headerOrdersCount.setText("Total " + String.valueOf(mvpPresenter.getFullFillmentList().size()) + " Orders");

            pickedUpOrdersAdapter = new PickedUpOrdersAdapter(this, mvpPresenter.getFullFillmentList(), this, mvpPresenter.getListOfListFullFillmentList(), false);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            activityPickedUpOrdersBinding.fullfilmentRecycler.setLayoutManager(mLayoutManager1);
            activityPickedUpOrdersBinding.fullfilmentRecycler.setItemAnimator(new DefaultItemAnimator());
            activityPickedUpOrdersBinding.fullfilmentRecycler.setAdapter(pickedUpOrdersAdapter);
        }else {
            activityPickedUpOrdersBinding.zeropicked.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void startPickUp() {

    }
private boolean removeItsStatis;
    @Override
    public void onClickScanCode() {
        if(removeItsStatis) {
            IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.ALL_CODE_TYPES);
            intentIntegrator.setBeepEnabled(false);
            intentIntegrator.setCameraId(0);
            intentIntegrator.setPrompt("SCAN");
            intentIntegrator.setBarcodeImageEnabled(false);
            intentIntegrator.initiateScan();
        }
    }

    @Override
    public void onItemClick(int position, String status, List<RackAdapter.RackBoxModel.ProductData> productDataList, List<RacksDataResponse.FullfillmentDetail> fullFillModelList, RacksDataResponse.FullfillmentDetail fillModel) {
        Gson gson = new Gson();
        String myJson = gson.toJson(productDataList);

        Gson gson1 = new Gson();
        String myJson1 = gson1.toJson(fullFillModelList);


        startActivityForResult(PickUpVerificationActivity.getStartActivity(PickedUpOrdersActivity.this, position, status, myJson, myJson1, fillModel), PICKUP_VERIFICATION_ACTIVITY);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult Result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (Result != null) {
            if (Result.getContents() == null) {
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Scanned -> " + Result.getContents(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PickedUpOrdersActivity.this, PickUpVerificationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICKUP_VERIFICATION_ACTIVITY:
                    if (data != null) {
                        int position = (int) data.getIntExtra("position", 0);
                        Gson gson = new Gson();
                        String json = data.getStringExtra("productDataList");
                        Type type = new TypeToken<List<RackAdapter.RackBoxModel.ProductData>>() {
                        }.getType();
                        if (productDataList != null) {
                            productDataList.clear();
                        }
                        productDataList = gson.fromJson(json, type);

                        Gson gson1 = new Gson();
                        String json1 = data.getStringExtra("fullFillModelList");
                        Type type1 = new TypeToken<List<RacksDataResponse.FullfillmentDetail>>() {
                        }.getType();
                        if (fullfillmentDetailList != null) {
                            fullfillmentDetailList.clear();
                        }
                        fullfillmentDetailList = gson1.fromJson(json1, type1);

                        List<RacksDataResponse.FullfillmentDetail.Product> productsList = new ArrayList<>();
                        RacksDataResponse.FullfillmentDetail.Product product = new RacksDataResponse.FullfillmentDetail.Product();

                        RacksDataResponse.FullfillmentDetail fullfillmentDetail = new RacksDataResponse.FullfillmentDetail();
                        for (int i = 0; i < fullfillmentDetailList.size(); i++) {
                            fullfillmentDetail.setFullfillmentId(fullfillmentDetailList.get(i).getFullfillmentId());
                            fullfillmentDetail.setTotalItems(fullfillmentDetailList.get(i).getTotalItems());
                            fullfillmentDetail.setStatus(fullfillmentDetailList.get(i).getStatus());
                            fullfillmentDetail.setSelectedBoxesData(fullfillmentDetailList.get(i).isSelectedBoxesData());
                            fullfillmentDetail.setExpandStatus(fullfillmentDetailList.get(i).getExpandStatus());
                            fullfillmentDetail.setBoxId(fullfillmentDetailList.get(i).getBoxId());
                            for (int j = 0; j < fullfillmentDetailList.get(i).getProducts().size(); j++) {
                                for (int k = 0; k < productDataList.size(); k++) {
                                    if (fullfillmentDetailList.get(i).getProducts().get(j).getProductId().equalsIgnoreCase(productDataList.get(k).getProductId())) {
                                        fullfillmentDetailList.get(i).getProducts().get(j).setPackerStatus(productDataList.get(k).getPackerStatus());
                                    }
                                }
                            }
                        }


                        mvpPresenter.setFullFillmentDataList(fullfillmentDetailList);

                        pickedUpOrdersAdapter = new PickedUpOrdersAdapter(this, mvpPresenter.getFullFillmentList(), this, mvpPresenter.getListOfListFullFillmentList(), false);
                        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                        activityPickedUpOrdersBinding.fullfilmentRecycler.setLayoutManager(mLayoutManager1);
                        activityPickedUpOrdersBinding.fullfilmentRecycler.setItemAnimator(new DefaultItemAnimator());
                        activityPickedUpOrdersBinding.fullfilmentRecycler.setAdapter(pickedUpOrdersAdapter);
                    }
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(DeciderScreen.getStartActivity(PickedUpOrdersActivity.this));
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
