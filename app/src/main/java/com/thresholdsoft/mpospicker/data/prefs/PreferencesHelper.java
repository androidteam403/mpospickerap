package com.thresholdsoft.mpospicker.data.prefs;

import com.thresholdsoft.mpospicker.data.utils.LoggedInMode;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.util.List;

public interface PreferencesHelper {
    int getUserLoggedInMode();

    void setUserLoggedIn(LoggedInMode mode);

    Long getUserId();

    void setUserId(Long userId);

    String getUserName();

    void setUserName(String userName);

    String getUserEmail();

    void setUserEmail(String email);

    String getUserProfilePicUrl();

    void setUserProfilePicUrl(String profilePicUrl);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getUserMobile();

    void setUserMobile(String mobileNumber);

    boolean isCoachMarkView();

    void setCoachMarkView(boolean coachMark);

    boolean isFirstTime();

    void setFirstTime(boolean firstTime);

    void logoutUser();

    void setFullFillmentList(List<RacksDataResponse.FullfillmentDetail> fullfillmentDetailList);

    List<RacksDataResponse.FullfillmentDetail> getFullFillmentList();

    void setfullFillListOfListFiltered(List<List<RackAdapter.RackBoxModel.ProductData>> fullFillListOfListFiltered);

    List<List<RackAdapter.RackBoxModel.ProductData>> getfullFillListOfListFiltered();
}
