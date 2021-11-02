package com.thresholdsoft.mpospicker.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created on : Nov 02, 2021
 * Author     : NAVEEN
 */


public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
