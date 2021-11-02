package com.thresholdsoft.mpospicker.di.component;


import com.thresholdsoft.mpospicker.di.PerActivity;
import com.thresholdsoft.mpospicker.di.module.ActivityModule;
import com.thresholdsoft.mpospicker.ui.login.LoginActivity;
import com.thresholdsoft.mpospicker.ui.main.MainActivity;

import dagger.Component;

/**
 * Created on : March 30, 2020
 * Author     : JAGADEESH
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);
}