package com.dintech.api.samples;

import android.app.Application;

import com.dintech.api.bleep.Blueteeth;

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        VLog.initialize("com.dintech.getApiService.log")
//                .logLevel(LogLevel.FULL)
//                .logTool(new AndroidLogTool())
//                .methodCount(3)
//                .methodOffset(10);

        Blueteeth.init()
                .application(this)
                .serviceUuid("")
                .characterUuid("")
                .trigger(new SystemTrigger(this))
                .split(false);
    }
}
