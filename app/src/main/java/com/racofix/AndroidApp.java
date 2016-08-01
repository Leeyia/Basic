package com.racofix;

import com.android.core.MainApp;
import com.android.core.control.crash.AndroidCrash;
import com.android.core.control.crash.HttpReportCallback;
import com.android.core.control.logcat.Logcat;
import com.android.core.model.LogicProxy;
import com.racofix.presenter.LoginContract;
import com.racofix.presenter.MainContract;

import java.io.File;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public class AndroidApp extends MainApp {

    @Override
    public void onCreate() {
        super.onCreate();

        LogicProxy.getInstance().init(
                LoginContract.class, MainContract.class);

        //Android crash 上传服务器回掉
        HttpReportCallback report = new HttpReportCallback() {
            @Override
            public void uploadException2remote(File file) {
                //可以直接上传文件
            }
        };
        AndroidCrash.getInstance().setCrashReporter(report).init(this);
        if (BuildConfig.DEBUG)
            Logcat.init("com.android.racofix").hideThreadInfo().methodCount(3);
    }
}
