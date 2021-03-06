package com.thresholdsoft.mpospicker.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thresholdsoft.mpospicker.data.utils.LoggedInMode;
import com.thresholdsoft.mpospicker.di.ApplicationContext;
import com.thresholdsoft.mpospicker.di.PreferenceInfo;
import com.thresholdsoft.mpospicker.root.AppConstant;
import com.thresholdsoft.mpospicker.ui.pickupprocess.adapter.RackAdapter;
import com.thresholdsoft.mpospicker.ui.pickupprocess.model.RacksDataResponse;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class PreferencesManager implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_LOGGED_IN_MODE";
    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
    private static final String PREF_KEY_USER_MOBILE = "PREF_KEY_CURRENT_MOBILE";
    private static final String PREF_KEY_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_FIRST_TIME = "PREF_KEY_FIRST_TIME";
    private static final String PREF_KEY_USER_PROFILE_PIC_URL = "PREF_KEY_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_COACH_MARK = "PREF_KEY_COACH_MARK";
    private static final String PREF_KEY_FULLFILLMENT_DETAILS = "PREF_KEY_FULLFILLMENT_DETAILS";
    private static final String PREF_KEY_FULLFILLMENT_LIST_OF_LIST_DETAILS = "PREF_KEY_FULLFILLMENT_LIST_OF_LIST_DETAILS";

    private final SharedPreferences mPrefs;
    private Context mAppContext;

    @Inject
    public PreferencesManager(@ApplicationContext Context context,
                              @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        mAppContext = context;
    }

    @Override
    public String getUserName() {
        return mPrefs.getString(PREF_KEY_USER_NAME, null);
    }

    @Override
    public void setUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_USER_NAME, userName).apply();
    }

    @Override
    public String getUserEmail() {
        return mPrefs.getString(PREF_KEY_USER_EMAIL, null);
    }

    @Override
    public void setUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_USER_EMAIL, email).apply();
    }

    @Override
    public String getUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public int getUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                LoggedInMode.LOGGED_IN_MODE_LOGOUT.getType());
    }

    @Override
    public void setUserLoggedIn(LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public Long getUserId() {
        return mPrefs.getLong(PREF_KEY_USER_ID, 0);
    }

    @Override
    public void setUserId(Long mUserId) {
        mPrefs.edit().putLong(PREF_KEY_USER_ID, mUserId).apply();
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getUserMobile() {
        return mPrefs.getString(PREF_KEY_USER_MOBILE, "");
    }

    @Override
    public void setUserMobile(String mobileNumber) {
        mPrefs.edit().putString(PREF_KEY_USER_MOBILE, mobileNumber).apply();
    }

    @Override
    public boolean isCoachMarkView() {
        SharedPreferences pref = mAppContext.getSharedPreferences(AppConstant.SHARED_PREF, 0);
        return pref.getBoolean(PREF_KEY_COACH_MARK, true);
    }

    @Override
    public void setCoachMarkView(boolean coachMark) {
        SharedPreferences pref = mAppContext.getSharedPreferences(AppConstant.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(PREF_KEY_COACH_MARK, coachMark);
        editor.apply();

    }

    @Override
    public boolean isFirstTime() {
        SharedPreferences pref = mAppContext.getSharedPreferences(AppConstant.SHARED_PREF, 0);
        return pref.getBoolean(PREF_KEY_FIRST_TIME, true);
    }

    @Override
    public void setFirstTime(boolean firstTime) {
        SharedPreferences pref = mAppContext.getSharedPreferences(AppConstant.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(PREF_KEY_FIRST_TIME, firstTime);
        editor.apply();
    }

    @Override
    public void logoutUser() {
        mPrefs.edit().clear().apply();
    }

    @Override
    public void setFullFillmentList(List<RacksDataResponse.FullfillmentDetail> fullfillmentDetailList) {
        mPrefs.edit().putString(PREF_KEY_FULLFILLMENT_DETAILS, new Gson().toJson(fullfillmentDetailList)).apply();
    }

    @Override
    public List<RacksDataResponse.FullfillmentDetail> getFullFillmentList() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_KEY_FULLFILLMENT_DETAILS, "");
        Type type = new TypeToken<List<RacksDataResponse.FullfillmentDetail>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    @Override
    public void setfullFillListOfListFiltered(List<List<RackAdapter.RackBoxModel.ProductData>> fullFillListOfListFiltered) {
        mPrefs.edit().putString(PREF_KEY_FULLFILLMENT_LIST_OF_LIST_DETAILS, new Gson().toJson(fullFillListOfListFiltered)).apply();
    }

    @Override
    public List<List<RackAdapter.RackBoxModel.ProductData>> getfullFillListOfListFiltered() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_KEY_FULLFILLMENT_LIST_OF_LIST_DETAILS, "");
        Type type = new TypeToken<List<List<RackAdapter.RackBoxModel.ProductData>>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
