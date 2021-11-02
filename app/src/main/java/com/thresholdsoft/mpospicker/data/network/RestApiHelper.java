package com.thresholdsoft.mpospicker.data.network;

import com.thresholdsoft.mpospicker.data.network.pojo.FeedItem;
import com.thresholdsoft.mpospicker.data.network.pojo.LoginRequest;
import com.thresholdsoft.mpospicker.data.network.pojo.UserProfile;
import com.thresholdsoft.mpospicker.data.network.pojo.WrapperResponse;

import java.util.List;

import io.reactivex.Single;

public interface RestApiHelper {

    Single<WrapperResponse<UserProfile>> doLoginApiCall(LoginRequest request);

    Single<WrapperResponse<List<FeedItem>>> getFeedList();
}
