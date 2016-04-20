package com.common;

import android.app.Application;
import android.content.Context;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public class BasicApplication extends Application {

    private static BasicApplication ourInstance = new BasicApplication();
    private static Context mContext;

    public static BasicApplication getInstance() {
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
