package com.racofix.basic.things.ui;

import android.util.Log;

import com.racofix.basic.mvp.HttpVo;
import com.racofix.basic.mvp.LogicImpl;

public class LoginLogic extends LogicImpl<HttpVo<String>> {

    @Override
    public void onLogicCreated() {
        super.onLogicCreated();
        Log.d("sssss", "onLogicCreated()");
        getVo().onResponse("zhangsan");
    }

    @Override
    public void onLogicDestroy() {
        super.onLogicDestroy();
        Log.d("sssss", "onLogicDestroy()");
    }
}
