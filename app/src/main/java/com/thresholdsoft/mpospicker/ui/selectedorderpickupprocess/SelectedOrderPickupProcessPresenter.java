package com.thresholdsoft.mpospicker.ui.selectedorderpickupprocess;

import android.util.Log;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.data.network.retrofit.ApiClient;
import com.thresholdsoft.mpospicker.data.network.retrofit.ApiInterface;
import com.thresholdsoft.mpospicker.ui.base.BasePresenter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Response;

public class SelectedOrderPickupProcessPresenter<V extends SelectedOrderPickupProcessMvpView> extends BasePresenter<V>
        implements SelectedOrderPickupProcessMvpPresenter<V> {
    @Inject
    public SelectedOrderPickupProcessPresenter(DataManager manager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(manager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onClickBack() {
        getMvpView().onClickBack();
    }

    @Override
    public void onRackApiCall() {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            getMvpView().hideKeyboard();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<RacksDataResponse> call = apiInterface.doRackApiCall();
            call.enqueue(new retrofit2.Callback<RacksDataResponse>() {
                @Override
                public void onResponse(Call<RacksDataResponse> call, Response<RacksDataResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            getMvpView().hideLoading();
                            getMvpView().onSuccessRackApi(response.body());
                        }
                    }
                    Log.e("TAG", response.code() + "");
                }

                @Override
                public void onFailure(Call<RacksDataResponse> call, Throwable t) {
                    Log.e("Service", "Failed res :: " + t.getMessage());
                    getMvpView().hideLoading();
                    getMvpView().showMessage("Something went wrong");
                }
            });
        } else {
            getMvpView().onError("Internet Connection Not Available");
        }
    }

    @Override
    public void onClickStausIcon() {
        getMvpView().onClickStausIcon();
    }

    @Override
    public void onClickFullPicked() {
        getMvpView().onClickFullPicked();
    }

    @Override
    public void onClickPartialPicked() {
        getMvpView().onClickPartialPicked();
    }

    @Override
    public void onClickNotAvailable() {
        getMvpView().onClickNotAvailable();
    }

    @Override
    public void onClickSkip() {
        getMvpView().onClickSkip();
    }

    @Override
    public void onClickBatchDetails() {
        getMvpView().onClickBatchDetails();
    }
}
