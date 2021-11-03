package com.thresholdsoft.mpospicker.di.component;


import com.thresholdsoft.mpospicker.di.PerActivity;
import com.thresholdsoft.mpospicker.di.module.ActivityModule;
import com.thresholdsoft.mpospicker.ui.login.LoginActivity;
import com.thresholdsoft.mpospicker.ui.main.MainActivity;
import com.thresholdsoft.mpospicker.ui.readyforpickup.ReadyForPickUpActivity;

import dagger.Component;

/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(ReadyForPickUpActivity readyForPickUpActivity);
}