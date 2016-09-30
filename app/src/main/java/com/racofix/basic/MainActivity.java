package com.racofix.basic;

import android.os.Bundle;

import com.android.core.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }

    @Override
    protected Class getLogic() {
        return null;
    }
}
