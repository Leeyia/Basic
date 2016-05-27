package com.zhoujinlong;

import com.android.core.MainApp;
import com.android.core.model.control.LogicProxy;
import com.zhoujinlong.presenter.CompeteLogic;
import com.zhoujinlong.presenter.LoginLogic;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public class AndroidApp extends MainApp {

    @Override
    public void onCreate() {
        super.onCreate();

        LogicProxy.getInstance().init(
                LoginLogic.class, CompeteLogic.class);
    }
}
