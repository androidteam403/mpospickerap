package com.thresholdsoft.mpospicker.data.network.retrofit;


import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("https://jsonblob.com/api/jsonBlob/907667560661794816")//907253572044079104
    Call<RacksDataResponse> doRackApiCall();
}