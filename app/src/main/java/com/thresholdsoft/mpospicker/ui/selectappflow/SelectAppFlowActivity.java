package com.thresholdsoft.mpospicker.ui.selectappflow;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.mpospicker.R;
import com.thresholdsoft.mpospicker.databinding.ActivitySelectAppFlowBinding;
import com.thresholdsoft.mpospicker.ui.base.BaseActivity;
import com.thresholdsoft.mpospicker.ui.billerflow.billerOrdersScreen.BillerOrdersActivity;
import com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders.PickedUpOrdersActivity;
import com.thresholdsoft.mpospicker.ui.pickerhome.PickerNavigationActivity;
import com.thresholdsoft.mpospicker.ui.selectappflow.adapter.SelectAppFlowListAdapter;
import com.thresholdsoft.mpospicker.ui.selectappflow.model.SelectAppFlowModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SelectAppFlowActivity extends BaseActivity implements SelectAppFlowMvpView {
    @Inject
    SelectAppFlowMvpPresenter<SelectAppFlowMvpView> mPresenter;
    private ActivitySelectAppFlowBinding selectAppFlowBinding;
    private SelectAppFlowListAdapter selectAppFlowListAdapter;
    private final List<SelectAppFlowModel> selectAppFlowModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectAppFlowBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_app_flow);
        getActivityComponent().inject(this);
        mPresenter.onAttach(SelectAppFlowActivity.this);
        setUp();

    }

    @Override
    protected void setUp() {
        getSelectAppFlowModelList();
        selectAppFlowBinding.setCallback(mPresenter);
        selectAppFlowListAdapter = new SelectAppFlowListAdapter(this, selectAppFlowModelList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        selectAppFlowBinding.appFlowListRecycler.setLayoutManager(mLayoutManager);
        selectAppFlowBinding.appFlowListRecycler.setAdapter(selectAppFlowListAdapter);
    }

    private void getSelectAppFlowModelList() {
        SelectAppFlowModel selectAppFlowModel = new SelectAppFlowModel();
        selectAppFlowModel.setAppFlowName("Picker");
        selectAppFlowModel.setSelected(true);
        selectAppFlowModelList.add(selectAppFlowModel);

        selectAppFlowModel = new SelectAppFlowModel();
        selectAppFlowModel.setAppFlowName("Packer");
        selectAppFlowModel.setSelected(false);
        selectAppFlowModelList.add(selectAppFlowModel);

        selectAppFlowModel = new SelectAppFlowModel();
        selectAppFlowModel.setAppFlowName("Biller");
        selectAppFlowModel.setSelected(false);
        selectAppFlowModelList.add(selectAppFlowModel);

        selectAppFlowModel = new SelectAppFlowModel();
        selectAppFlowModel.setAppFlowName("Sealer");
        selectAppFlowModel.setSelected(false);
        selectAppFlowModelList.add(selectAppFlowModel);

        selectAppFlowModel = new SelectAppFlowModel();
        selectAppFlowModel.setAppFlowName("Admin");
        selectAppFlowModel.setSelected(false);
        selectAppFlowModelList.add(selectAppFlowModel);
    }

    @Override
    public void onClickContinue() {
        for (int i = 0; i < selectAppFlowModelList.size(); i++) {
            if (selectAppFlowModelList.get(i).isSelected()) {
                switch (selectAppFlowModelList.get(i).getAppFlowName()) {
                    case "Picker":
                        startActivity(PickerNavigationActivity.getStartIntent(SelectAppFlowActivity.this));
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        break;
                    case "Packer":
                        startActivity(PickedUpOrdersActivity.getStartActivity(SelectAppFlowActivity.this));
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        break;
                    case "Biller":
//                        startActivity(BillerOrdersActivity.(SelectAppFlowActivity.this));
//                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        break;
                    case "Sealer":
                        break;
                    case "Admin":
                        break;
                    default:
                }
                break;
            }
        }
    }

    @Override
    public void onClickSelectAppFlowItem(int pos) {
        for (int i = 0; i < selectAppFlowModelList.size(); i++) {
            selectAppFlowModelList.get(i).setSelected(i == pos);
        }
        selectAppFlowListAdapter.notifyDataSetChanged();
    }
}



