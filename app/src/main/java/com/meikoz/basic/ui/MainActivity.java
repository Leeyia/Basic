package com.meikoz.basic.ui;

import android.os.Bundle;

import com.meikoz.basic.R;
import com.meikoz.basic.presenter.MainLogicI;
import com.meikoz.core.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainLogicI.MainView{

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView(Bundle bundle) {
    }

    @Override
    protected Class getLogicClazz() {
        return MainLogicI.class;
    }

    @Override
    protected void onInitData2Remote() {
        super.onInitData2Remote();
    }

    @Override
    public void onLoadSuccessHandler(String responce) {

    }
}
