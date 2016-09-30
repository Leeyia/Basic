package com.android.core;

import android.app.Application;
import android.content.Context;

/**
 * @User: 蜡笔小新
 * @date: 16-9-30
 * @GitHub: https://github.com/meikoz
 */
public class MainApplication extends Application {

    private static MainApplication ourInstance = new MainApplication();
    private static Context mContext;

    public static MainApplication getInstance() {
        return ourInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        mContext = getApplicationContext();
    }
}
