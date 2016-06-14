package com.zhoujinlong.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zhoujinlong.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-14 10:38
 * @GitHub: https://github.com/meikoz
 */
public class Crash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    void onClick(View view) {
        if (view != null)
            throw new RuntimeException("自定义异常，这是自己抛出去的");
    }

}
