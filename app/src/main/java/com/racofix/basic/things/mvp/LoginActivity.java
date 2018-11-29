package com.racofix.basic.things.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.racofix.basic.http.GsonService;
import com.racofix.basic.mvp.BaseActivity;
import com.racofix.basic.mvp.annotation.Implement;
import com.racofix.basic.things.R;

import java.util.List;

@Implement(LoginLogicImpl.class)
public class LoginActivity extends BaseActivity<LoginLogicImpl> implements LoginLogic.Vo {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_login).setOnClickListener(view -> getLogicImpl().login("admin", "admin"));
        List<String> sssss = GsonService.Factory.create().parseArray("sssss", String.class);
    }

    @Override
    public void successful(String message) {
    }

    @Override
    public void failed(String failMsg) {
    }
}
