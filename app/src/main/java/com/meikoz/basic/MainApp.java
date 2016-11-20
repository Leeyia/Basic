package com.meikoz.basic;

import com.meikoz.core.MainApplication;
import com.meikoz.core.api.RestApi;

/**
 * @User: 蜡笔小新
 * @date: 16-11-1
 * @GitHub: https://github.com/meikoz
 */

public class MainApp extends MainApplication {
    @Override
    public void onCreate() {
        RestApi.getInstance().deBug(true);
        super.onCreate();
    }
}
