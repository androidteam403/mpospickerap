package com.thresholdsoft.mpospicker.root;

import android.app.Application;

import com.thresholdsoft.mpospicker.di.component.ApplicationComponent;
import com.thresholdsoft.mpospicker.di.component.DaggerApplicationComponent;
import com.thresholdsoft.mpospicker.di.module.ApplicationModule;

/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */
public class ApplicatinApp extends Application {


    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
