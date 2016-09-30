package com.racofix.basic;

import com.android.core.MainApplication;
import com.android.core.manage.log.Logger;

/**
 * @User: 蜡笔小新
 * @date: 16-9-30
 * @GitHub: https://github.com/meikoz
 */

public class MainApp extends MainApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG)
            Logger.init(getPackageName()).hideThreadInfo().methodCount(3);
    }
}
