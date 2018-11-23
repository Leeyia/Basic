package com.racofix.basic.things.ui;

import com.racofix.basic.mvp.BaseLogicImpl;

public class LoginLogicImpl extends BaseLogicImpl<LoginLogic.Vo> implements LoginLogic.Logic {

    @Override
    public void onLogicCreated() {
        super.onLogicCreated();
        // LoginLogicImpl Created
    }

    @Override
    public void onLogicDestroy() {
        super.onLogicDestroy();
        // LoginLogicImpl Destroy
    }

    @Override
    public void login(String username, String password) {

    }
}
