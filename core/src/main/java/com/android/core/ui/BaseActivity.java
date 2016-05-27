package com.android.core.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import butterknife.ButterKnife;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-26 17:17
 * @GitHub: https://github.com/meikoz
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        onInitView();
        onInitData();
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();

    protected abstract void onInitData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        // 打开Activity动画
    }

    @Override
    public void finish() {
        super.finish();
        // 关闭动画
    }
}
