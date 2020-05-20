package com.racofix.things2.example.mvp;

import com.racofix.things2.mvp.BaseLogicImpl;

public class Register extends BaseLogicImpl<RegisterI.View> implements RegisterI.Logic {
    @Override
    public void sign_up(String username) {
        getView().sign_up_success();
    }
}
