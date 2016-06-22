package com.zhoujinlong;

import com.android.core.MainApp;
import com.android.core.control.crash.HttpReportCallback;
import com.android.core.model.control.LogicProxy;
import com.android.core.control.crash.AndroidCrash;
import com.zhoujinlong.presenter.ILogin;
import com.zhoujinlong.presenter.IMain;

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
                ILogin.class, IMain.class);

        //Android crash 上传服务器回掉
        HttpReportCallback report = new HttpReportCallback() {
            @Override
            public void uploadException2remote(File file) {
                //可以直接上传文件
            }
        };
        AndroidCrash.getInstance().setCrashReporter(report).init(this);
    }
}
