package com.android.core;

import android.app.Application;
import android.content.Context;


/**
 * @author: 蜡笔小新
 * @date: 2016-05-26 17:31
 * @GitHub: https://github.com/meikoz
 */
public class MainApp extends Application {

    private static MainApp ourInstance = new MainApp();
    private static Context mContext;

    public static MainApp getInstance() {
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
