package com.thresholdsoft.mpospicker.di.module;

import android.content.Context;

import com.thresholdsoft.mpospicker.di.ActivityContext;
import com.thresholdsoft.mpospicker.di.PerActivity;
import com.thresholdsoft.mpospicker.ui.login.LoginMvpPresenter;
import com.thresholdsoft.mpospicker.ui.login.LoginMvpView;
import com.thresholdsoft.mpospicker.ui.login.LoginPresenter;
import com.thresholdsoft.mpospicker.ui.main.MainMvpPresenter;
import com.thresholdsoft.mpospicker.ui.main.MainMvpView;
import com.thresholdsoft.mpospicker.ui.main.MainPresenter;
import com.thresholdsoft.mpospicker.ui.main.RssAdapter;
import com.thresholdsoft.mpospicker.utils.rx.AppSchedulerProvider;
import com.thresholdsoft.mpospicker.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */
@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }


    @Provides
    RssAdapter provideRssAdapter() {
        return new RssAdapter(new ArrayList<>());
    }
}