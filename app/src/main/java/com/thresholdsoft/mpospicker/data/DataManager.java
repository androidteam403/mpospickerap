package com.thresholdsoft.mpospicker.data;

import com.thresholdsoft.mpospicker.data.db.dao.UserDao;
import com.thresholdsoft.mpospicker.data.network.RestApiHelper;
import com.thresholdsoft.mpospicker.data.prefs.PreferencesHelper;
import com.thresholdsoft.mpospicker.data.utils.LoggedInMode;

public interface DataManager extends UserDao, PreferencesHelper, RestApiHelper {
    void updateApiHeader(Long userId, String accessToken);

    void setUserLoggedOut();

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath);
}
