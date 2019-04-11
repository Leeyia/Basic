package com.dintech.api.samples;

import android.app.Application;

import com.dintech.api.log.AndroidLogTool;
import com.dintech.api.log.VLog;
import com.dintech.api.log.LogLevel;

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
