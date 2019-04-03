package com.dintech.android.samples;

import android.app.Application;

import com.dintech.android.log.AndroidLogTool;
import com.dintech.android.log.VLog;
import com.dintech.android.log.LogLevel;

public class VApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VLog.initialize("com.dintech.api.log")
                .logLevel(LogLevel.FULL)
                .logTool(new AndroidLogTool())
                .methodCount(3)
                .methodOffset(10);
    }
}
