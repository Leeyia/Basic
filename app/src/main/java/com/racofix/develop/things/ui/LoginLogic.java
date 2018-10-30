package com.racofix.develop.things.ui;

import android.util.Log;

import com.racofix.develop.mvp.HttpVo;
import com.racofix.develop.mvp.LogicImpl;

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
