package com.meikoz.basic;

import com.meikoz.basic.app.Constants;
import com.meikoz.core.MainApplication;
import com.meikoz.core.api.RestApi;
import com.meikoz.core.manage.crash.AndroidCrash;
import com.meikoz.core.manage.interfacee.HttpReportCallback;

import java.io.File;

/**
 * @User: 蜡笔小新
 * @date: 16-11-1
 * @GitHub: https://github.com/meikoz
 */

public class MainApp extends MainApplication {
    @Override
    public void onCreate() {
        RestApi.getInstance().bug(Constants.Config.DEVELOPER_MODE);
        super.onCreate();

        configure();
    }

    private void configure() {
        HttpReportCallback callback = new HttpReportCallback() {
            @Override
            public void uploadException2remote(File file) {
                // param file is exception file
            }
        };
        AndroidCrash.getInstance().setCrashReporter(callback).init(this);
    }
}
