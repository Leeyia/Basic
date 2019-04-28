package com.dintech.api.samples;

import android.app.Application;

import com.dintech.api.bleep.BluetoothKit;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        VLog.initialize("com.dintech.getApiService.log")
//                .logLevel(LogLevel.FULL)
//                .logTool(new AndroidLogTool())
//                .methodCount(3)
//                .methodOffset(10);

        BluetoothKit.init()
                .application(this)
                .serviceUuid("")
                .characterUuid("")
                .trigger(new SystemTrigger(this))
                .split(false);
    }
}
