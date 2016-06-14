package com.zhoujinlong;

import com.android.core.MainApp;
import com.android.core.model.control.LogicProxy;
import com.android.core.control.crash.AndroidCrash;
import com.zhoujinlong.presenter.LoginLogic;
import com.zhoujinlong.presenter.MainLogic;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public class AndroidApp extends MainApp {

    @Override
    public void onCreate() {
        super.onCreate();

        LogicProxy.getInstance().init(
                LoginLogic.class, MainLogic.class);

        AndroidCrash.getInstance().init(this);
    }
}
