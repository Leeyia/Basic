package com.racofix.basic.things.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.racofix.basic.mvp.BaseActivity;
import com.racofix.basic.mvp.annotation.Implement;
import com.racofix.basic.things.R;

@Implement(LoginLogicImpl.class)
public class LoginActivity extends BaseActivity<LoginLogicImpl> implements LoginLogic.Vo {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_login).setOnClickListener(view -> getLogicImpl().login("admin", "admin"));
    }

    @Override
    public void successful(String message) {
    }

    @Override
    public void failed(String failMsg) {
    }
}
