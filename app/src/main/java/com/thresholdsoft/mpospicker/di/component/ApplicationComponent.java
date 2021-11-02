package com.thresholdsoft.mpospicker.di.component;

import android.app.Application;
import android.content.Context;

import com.thresholdsoft.mpospicker.data.DataManager;
import com.thresholdsoft.mpospicker.di.ApplicationContext;
import com.thresholdsoft.mpospicker.di.module.ApplicationModule;
import com.thresholdsoft.mpospicker.root.ApplicatinApp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(ApplicatinApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
//    @Component.Builder
//    interface Builder {
//        ApplicationComponent build();
//
//        Builder applicationModule(ApplicationModule applicationModule);
//
//        DataManager getDataManager();
//    }


}
