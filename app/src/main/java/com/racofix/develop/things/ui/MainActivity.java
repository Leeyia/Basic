package com.racofix.develop.things.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.racofix.develop.mvp.BaseActivity;
import com.racofix.develop.mvp.annotation.Logic;
import com.racofix.develop.things.R;

@Logic(LoginLogic.class)
public class MainActivity extends BaseActivity<LoginLogic> implements HttpVo<String> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void successful(String s) {
        
    }

    @Override
    public void failed(String message) {

    }
}
