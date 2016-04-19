package com.matto;

import com.common.BasicApplication;
import com.common.model.control.LogicProxy;
import com.matto.model.LoginLogic;
import com.matto.model.MainLogic;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public class App extends BasicApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        LogicProxy.getInstance().init(
                LoginLogic.class, MainLogic.class
        );
    }
}
