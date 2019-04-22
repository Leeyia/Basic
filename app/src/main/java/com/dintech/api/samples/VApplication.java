package com.dintech.api.samples;

import android.app.Application;

import com.dintech.api.bleep.BluetoothKit;
import com.dintech.api.bleep.Configurations;


public class VApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        VLog.initialize("com.dintech.getApiService.log")
//                .logLevel(LogLevel.FULL)
//                .logTool(new AndroidLogTool())
//                .methodCount(3)
//                .methodOffset(10);

        Configurations configuration = new Configurations.Builder().build();
        BluetoothKit.init(this).configurations(configuration);
    }
}
