package com.dintech.android.samples;

import android.app.Application;

import com.dintech.architecture.log.AndroidLogTool;
import com.dintech.architecture.log.VLog;
import com.dintech.architecture.log.LogLevel;

public class DYApplication extends Application {

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
