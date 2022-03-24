package com.thresholdsoft.mpospicker.ui.mpospackerflow.pickeduporders;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PickedUpOrdersPresenter<V extends PickedUpOrdersMvpView> extends BasePresenter<V>
        implements PickedUpOrdersMvpPresenter<V> {

    @Inject
    public PickedUpOrdersPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void startPickUp() {
        getMvpView().startPickUp();
    }

    @Override
    public void onClickScanCode() {
        getMvpView().onClickScanCode();
    }

    @Override
    public List<RacksDataResponse.FullfillmentDetail> getFullFillmentList() {
        return getDataManager().getFullFillmentList();
    }

    @Override
    public List<List<RackAdapter.RackBoxModel.ProductData>> getListOfListFullFillmentList() {
        return getDataManager().getfullFillListOfListFiltered();
    }

    @Override
    public void setFullFillmentDataList(List<RacksDataResponse.FullfillmentDetail> fullFillmentDataList) {
        getDataManager().setFullFillmentList(fullFillmentDataList);
    }

    @Override
    public void setListOfListFullFillProducts(List<List<RackAdapter.RackBoxModel.ProductData>> listOfListFullFillProducts) {
        getDataManager().setfullFillListOfListFiltered(listOfListFullFillProducts);
    }

}
