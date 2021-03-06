package com.thresholdsoft.mpospicker.data.network;

import com.thresholdsoft.mpospicker.data.network.pojo.FeedItem;
import com.thresholdsoft.mpospicker.data.network.pojo.LoginRequest;
import com.thresholdsoft.mpospicker.data.network.pojo.UserProfile;
import com.thresholdsoft.mpospicker.data.network.pojo.WrapperResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class RestApiManager implements RestApiHelper {

    NetworkService mService;

    @Inject
    public RestApiManager(NetworkService apiService) {
        mService = apiService;
    }

    @Override
    public Single<WrapperResponse<UserProfile>> doLoginApiCall(LoginRequest request) {
        return mService.doLoginApiCall(request);
    }

    @Override
    public Single<WrapperResponse<List<FeedItem>>> getFeedList() {
        return mService.getFeedList();
    }
}
