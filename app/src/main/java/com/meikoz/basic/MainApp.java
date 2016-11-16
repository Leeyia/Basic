package com.meikoz.basic;

import com.meikoz.core.MainApplication;
import com.meikoz.core.api.RestApi;
import com.meikoz.core.manage.log.LogLevel;
import com.meikoz.core.manage.log.Logger;

/**
 * @User: 蜡笔小新
 * @date: 16-11-1
 * @GitHub: https://github.com/meikoz
 */

public class MainApp extends MainApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        RestApi.getInstance().init(true);
        Logger
                .init(getPackageName())
                .methodCount(3)
                .hideThreadInfo()
                .logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
    }
}
