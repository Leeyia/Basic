package com.racofix.things2.example.mvp;

import com.racofix.things2.mvp.BaseLogicImpl;

public class Login extends BaseLogicImpl<LoginI.View> implements LoginI.Logic {

    @Override
    public void sign_in(String username, String password) {
        /*do some things*/
        getView().sign_in_success();
    }
}
