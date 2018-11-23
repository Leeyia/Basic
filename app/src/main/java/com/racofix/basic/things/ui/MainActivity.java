package com.racofix.basic.things.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.racofix.basic.mvp.BaseAct;
import com.racofix.basic.mvp.annotation.Implement;
import com.racofix.basic.things.R;

@Implement(LoginLogicImpl.class)
public class MainActivity extends BaseAct<LoginLogicImpl> implements LoginContract.Vo {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLogic().login("s", "s");
    }

    @Override
    public void successful(String message) {

    }

    @Override
    public void failed(String failMsg) {

    }
}
