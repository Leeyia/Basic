package com.racofix.basic.things.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.racofix.basic.mvp.BaseLogicActivity;
import com.racofix.basic.mvp.annotation.Implement;
import com.racofix.basic.things.R;

@Implement(LoginLogicImpl.class)
public class LoginActivity extends BaseLogicActivity<LoginLogicImpl> implements LoginLogic.Vo {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getLogicImpl().login("admin", "admin");
    }

    @Override
    public void successful(String message) {

    }

    @Override
    public void failed(String failMsg) {

    }
}
