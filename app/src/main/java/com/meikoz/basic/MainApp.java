package com.meikoz.basic;

import com.meikoz.basic.app.Constants;
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
        RestApi.getInstance().bug(Constants.Config.DEVELOPER_MODE);
        super.onCreate();
    }
}
