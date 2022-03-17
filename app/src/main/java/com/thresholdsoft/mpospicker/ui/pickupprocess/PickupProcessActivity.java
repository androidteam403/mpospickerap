package com.thresholdsoft.mpospicker.ui.pickupprocess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivityPickupProcessBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.OrderAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.ui.pickupsummary.PickUpSummmaryActivityNew;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class PickupProcessActivity extends BaseActivity implements PickupProcessMvpView {

    @Inject
    PickupProcessMvpPresenter<PickupProcessMvpView> mPresenter;
    private ActivityPickupProcessBinding pickupProcessBinding;
    private OrderAdapter orderAdapter;
    private RackAdapter rackAdapter;
    private List<List<RackAdapter.RackBoxModel.ProductData>> rackListOfList = new ArrayList<>();
    private List<List<OrderAdapter.RackBoxModel.ProductData>> fullListOfList = new ArrayList<>();

    List<RacksDataResponse.FullfillmentDetail> racksDataResponse;
    private static List<RacksDataResponse.FullfillmentDetail.Product> rackIdList = new ArrayList<>();
    private ArrayList<String> boxStringList = new ArrayList<>();

    long startTime;
    long countUp;
    Chronometer stopWatch;

    public static Intent getStartActivity(Context context, List<RacksDataResponse.FullfillmentDetail> racksDataResponse) {
        Intent intent = new Intent(context, PickupProcessActivity.class);
        intent.putExtra("rackDataResponse", (Serializable) racksDataResponse);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pickupProcessBinding = DataBindingUtil.setContentView(this, R.layout.activity_pickup_process);
        getActivityComponent().inject(this);
        mPresenter.onAttach(PickupProcessActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        pickupProcessBinding.setCallback(mPresenter);
        if (getIntent() != null) {
            racksDataResponse = (List<RacksDataResponse.FullfillmentDetail>) getIntent().getSerializableExtra("rackDataResponse");

            for (int i = 0; i < racksDataResponse.size(); i++) {
                for (int j = 0; j < racksDataResponse.get(i).getProducts().size(); j++) {
                    rackIdList.add(racksDataResponse.get(i).getProducts().get(j));
                }
            }
            for (int i = 0; i < rackIdList.size(); i++) {
                for (int j = 0; j < rackIdList.size(); j++) {
                    if (i != j && rackIdList.get(i).getRackId().equals(rackIdList.get(j).getRackId())) {
                        rackIdList.remove(j);
                    }
                }
            }

//        rackIdList.get(0).setExpandStatus(1);
//        racksDataResponse.getFullfillmentDetails().get(0).setExpandStatus(1);

            rackAdapter = new RackAdapter(this, rackIdList, racksDataResponse, this, rackListOfList, false);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            pickupProcessBinding.rackRecycler.setLayoutManager(mLayoutManager);
            pickupProcessBinding.rackRecycler.setAdapter(rackAdapter);
        }
//        mPresenter.onRackApiCall();
        rackOrderCheckedListener();
        Glide.with(this).load("https://apis.v35.dev.zeroco.de/zc-v3.1-fs-svc/2.0/apollo_rider/get/41B8F83052E720DA0FC28401C9BFAA90396DCB4FD14F508D641DBC42F5808C634160E6E9BDFF4D97E46A107F1185330BE9BE56FEC6E2C512EC7E08CAAA498D8FA633B599A9A34C9C97BCF338231C7AA91F16F94D257D61803FBC97DE5FEEACF62933C5F49DFFBE9EBADD5C68A6A9245EE277F7369BEBB4A75B56F81CDA296FE0F45824C81F0E7A9C29BA1E691D49C48BCB3E2586250A732BC0C95D8C9A1E1154C38FC1DFED04C09C36722BD70B9D0E10952C6B12C3EABEF551397B781F83118196C4F5899C1A7EBB728DE8B78537C55B735B4BEAE021E0391CB1ACE72296B00A8869B3AA7F4BF1674AC2BF9952BF39A67ABCA6DC6BF69C69CCC9C5766F79B2F9").circleCrop().into(pickupProcessBinding.pickerImg);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
        String strDate = mdformat.format(calendar.getTime());
        pickupProcessBinding.time.setText(strDate);

        stopWatch = (Chronometer) findViewById(R.id.chrono);
        startTime = SystemClock.elapsedRealtime();

        stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer arg0) {
                countUp = (SystemClock.elapsedRealtime() - arg0.getBase()) / 1000;
                String asText = (countUp / 60) + ":" + (countUp % 60);
                pickupProcessBinding.timer.setText(asText);
            }
        });
        stopWatch.start();
    }

    @Override
    public void onSuccessRackApi(RacksDataResponse racksDataResponse) {
//        for (int i = 0; i < racksDataResponse.getFullfillmentDetails().size(); i++) {
//            for (int j = 0; j < racksDataResponse.getFullfillmentDetails().get(i).getProducts().size(); j++) {
//                rackIdList.add(racksDataResponse.getFullfillmentDetails().get(i).getProducts().get(j));
//            }
//        }
//        for (int i = 0; i < rackIdList.size(); i++) {
//            for (int j = 0; j < rackIdList.size(); j++) {
//                if (i != j && rackIdList.get(i).getRackId().equals(rackIdList.get(j).getRackId())) {
//                    rackIdList.remove(j);
//                }
//            }
//        }
//
////        rackIdList.get(0).setExpandStatus(1);
////        racksDataResponse.getFullfillmentDetails().get(0).setExpandStatus(1);
//
//        rackAdapter = new RackAdapter(this, rackIdList, racksDataResponse, this, rackListOfList, false);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//        pickupProcessBinding.rackRecycler.setLayoutManager(mLayoutManager);
//        pickupProcessBinding.rackRecycler.setAdapter(rackAdapter);
    }


    private void rackOrderCheckedListener() {
        pickupProcessBinding.rackOrderToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (rackListOfListFiltered != null)
                    rackAdapter = new RackAdapter(PickupProcessActivity.this, rackIdList, racksDataResponse, PickupProcessActivity.this, rackListOfListFiltered, false);
                else
                    rackAdapter = new RackAdapter(PickupProcessActivity.this, rackIdList, racksDataResponse, PickupProcessActivity.this, rackListOfList, false);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PickupProcessActivity.this);
                pickupProcessBinding.rackRecycler.setLayoutManager(mLayoutManager);
                pickupProcessBinding.rackRecycler.setAdapter(rackAdapter);
                Toast.makeText(PickupProcessActivity.this, "true", Toast.LENGTH_SHORT).show();
            } else {
//                if (fullfillmentListOfListFiltered != null)
//                    orderAdapter = new OrderAdapter(PickupProcessActivity.this, racksDataResponse, PickupProcessActivity.this, fullfillmentListOfListFiltered);
//                else
//                    orderAdapter = new OrderAdapter(PickupProcessActivity.this, racksDataResponse, PickupProcessActivity.this, fullListOfList);
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PickupProcessActivity.this);
//                pickupProcessBinding.rackRecycler.setLayoutManager(mLayoutManager);
//                pickupProcessBinding.rackRecycler.setAdapter(orderAdapter);
//                Toast.makeText(PickupProcessActivity.this, "false", Toast.LENGTH_SHORT).show();


                if (rackListOfListFiltered != null)
                    orderAdapter = new OrderAdapter(PickupProcessActivity.this, racksDataResponse, PickupProcessActivity.this, rackListOfListFiltered, false);
                else
                    orderAdapter = new OrderAdapter(PickupProcessActivity.this, racksDataResponse, PickupProcessActivity.this, rackListOfList, false);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PickupProcessActivity.this);
                pickupProcessBinding.rackRecycler.setLayoutManager(mLayoutManager);
                pickupProcessBinding.rackRecycler.setAdapter(orderAdapter);
                Toast.makeText(PickupProcessActivity.this, "false", Toast.LENGTH_SHORT).show();
            }
        });
    }

    List<List<RackAdapter.RackBoxModel.ProductData>> rackListOfListFiltered;

    @Override
    public void onRackStatusUpdate(List<List<RackAdapter.RackBoxModel.ProductData>> listOfList) {
        this.rackListOfListFiltered = listOfList;

//followed by code Just only to change  the continue btn color
        int statusCount = 0;
        int overallProductCount = 0;
        if (rackListOfList != null && rackListOfList.size() > 0) {

            for (int i = 0; i < racksDataResponse.size(); i++) {
                for (int j = 0; j < racksDataResponse.get(i).getProducts().size(); j++) {
                    for (int k = 0; k < rackListOfList.size(); k++) {
                        for (int l = 0; l < rackListOfList.get(k).size(); l++) {
                            if (racksDataResponse.get(i).getProducts().get(j).getProductId().equalsIgnoreCase(rackListOfList.get(k).get(l).getProductId())) {
                                racksDataResponse.get(i).getProducts().get(j).setFinalStatusUpdate(rackListOfList.get(k).get(l).isFinalStatusUpdate());
                            }
                        }
                    }
                }
            }
            if (rackListOfList != null && rackListOfList.size() > 0) {
                for (int i = 0; i < racksDataResponse.size(); i++) {
                    for (int j = 0; j < racksDataResponse.get(i).getProducts().size(); j++) {
                        overallProductCount = overallProductCount + 1;
                        if (racksDataResponse.get(i).getProducts().get(j).isFinalStatusUpdate()) {
                            statusCount = statusCount + 1;
                        }
                    }
                }
                if (statusCount == overallProductCount) {
                    pickupProcessBinding.continueBtn.setBackgroundColor(getResources().getColor(R.color.continue_select_color));
                    pickupProcessBinding.continueBtn.setTextColor(getResources().getColor(R.color.black));
                }
            }
        }

    }

    @Override
    public List<List<RackAdapter.RackBoxModel.ProductData>> productList() {
        return rackListOfListFiltered;
    }

    List<List<OrderAdapter.RackBoxModel.ProductData>> fullfillmentListOfListFiltered;

    @Override
    public void onFullfillmentOrderStatusUpdate(List<List<OrderAdapter.RackBoxModel.ProductData>> listOfList) {
        this.fullfillmentListOfListFiltered = listOfList;
    }

    @Override
    public List<List<OrderAdapter.RackBoxModel.ProductData>> fullfilListOfList() {
        return fullfillmentListOfListFiltered;
    }

    List<RackAdapter.RackBoxModel.ProductData> productsNextPosReturn;

    @Override
    public List<RackAdapter.RackBoxModel.ProductData> productsNextPosReturn(List<RackAdapter.RackBoxModel.ProductData> productsNextPosReturn) {
        this.productsNextPosReturn = productsNextPosReturn;
        return productsNextPosReturn;
    }

    @Override
    public List<RackAdapter.RackBoxModel.ProductData> productsNextPosReturn() {
        return productsNextPosReturn;
    }


    @Override
    public void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onClickContinue() {
        int statusCount = 0;
        int overallProductCount = 0;
        if (rackListOfList != null && rackListOfList.size() > 0) {

            for (int i = 0; i < racksDataResponse.size(); i++) {
                for (int j = 0; j < racksDataResponse.get(i).getProducts().size(); j++) {
                    for (int k = 0; k < rackListOfList.size(); k++) {
                        for (int l = 0; l < rackListOfList.get(k).size(); l++) {
                            if (racksDataResponse.get(i).getProducts().get(j).getProductId().equalsIgnoreCase(rackListOfList.get(k).get(l).getProductId())) {
                                racksDataResponse.get(i).getProducts().get(j).setFinalStatusUpdate(rackListOfList.get(k).get(l).isFinalStatusUpdate());
                            }
                        }
                    }
                }
            }
            if (rackListOfList != null && rackListOfList.size() > 0) {
                for (int i = 0; i < racksDataResponse.size(); i++) {
                    for (int j = 0; j < racksDataResponse.get(i).getProducts().size(); j++) {
                        overallProductCount = overallProductCount + 1;
                        if (racksDataResponse.get(i).getProducts().get(j).isFinalStatusUpdate()) {
                            statusCount = statusCount + 1;
                        }
                    }
                }
                if (statusCount == overallProductCount) {
                    stopWatch.stop();
                    Gson gson = new Gson();
                    String myJson = gson.toJson(rackListOfListFiltered);
                    String myFullFillJson = gson.toJson(fullfillmentListOfListFiltered);
                    startActivity(PickUpSummmaryActivityNew.getStartActivity(this, racksDataResponse, myJson, myFullFillJson, pickupProcessBinding.time.getText().toString(), pickupProcessBinding.timer.getText().toString()));
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                } else {
                    Toast.makeText(this, "Collect Every Product Required Quantity", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "Collect Every Product Required Quantity", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
