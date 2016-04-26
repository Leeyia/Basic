package com.matto;

import com.common.BasicApplication;
import com.common.model.control.LogicProxy;
import com.matto.presenter.LoginLogic;
import com.matto.presenter.MainLogic;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public class AndroidApp extends BasicApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        LogicProxy.getInstance().init(
                LoginLogic.class, MainLogic.class
        );
    }
}
