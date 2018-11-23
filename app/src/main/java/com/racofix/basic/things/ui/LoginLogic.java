package com.racofix.basic.things.ui;

import android.util.Log;

import com.racofix.basic.mvp.LogicImpl;

public class LoginLogic extends LogicImpl<LoginVo<String>> {

    @Override
    public void onLogicCreated() {
        super.onLogicCreated();
        Log.d("sssss", "onLogicCreated()");

        getVo().success("onLogicCreated");
    }

    @Override
    public void onLogicDestroy() {
        super.onLogicDestroy();
        Log.d("sssss", "onLogicDestroy()");
    }
}
