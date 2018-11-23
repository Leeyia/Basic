package com.racofix.basic.things.ui;

import android.os.Bundle;
import android.util.Log;

import com.racofix.basic.mvp.BaseLogicImpl;

public class LoginLogicImpl extends BaseLogicImpl<LoginContract.Vo> implements LoginContract.Logic {

    @Override
    public void onLogicCreated() {
        super.onLogicCreated();
        Log.d("sssss", "onLogicCreated()");

        getVo().successful("onLogicCreated");
    }

    @Override
    public Bundle getStateBundle() {
        Bundle stateBundle = super.getStateBundle();
        return stateBundle;
    }


    @Override
    public void onLogicDestroy() {
        super.onLogicDestroy();
        Log.d("sssss", "onLogicDestroy()");
    }

    @Override
    public void login(String username, String password) {

    }
}
