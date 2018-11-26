package com.racofix.basic.things.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.racofix.basic.mvp.LogicImpl;

public class LoginLogicImpl extends LogicImpl<LoginLogic.Vo> implements LoginLogic.Logic {

    @Override
    public void onLogicCreated() {
        super.onLogicCreated();
        // LoginLogicImpl Created
        Log.e("Basic", "onLogicCreated()");
    }

    @Override
    public void onLogicDestroy() {
        super.onLogicDestroy();
        // LoginLogicImpl Destroy
        Log.e("Basic", "onLogicDestroy()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onLogicResume() {
        Log.e("Basic", "onLogicResume()");
    }

    @Override
    public void login(String username, String password) {

    }
}
