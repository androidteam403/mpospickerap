package com.thresholdsoft.mpospicker.ui.main;

import com.thresholdsoft.mpospicker.data.network.pojo.FeedItem;
import com.thresholdsoft.mpospicker.ui.base.MvpView;

import java.util.List;

/**
 * Created on : Feb 11, 2019
 * Author     : AndroidWave
 */
public interface MainMvpView extends MvpView {
    void updateFeed(List<FeedItem> feedItemList);
}
