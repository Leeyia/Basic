package com.meikoz.basic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.meikoz.basic.R;
import com.meikoz.basic.app.ImageControl;
import com.meikoz.core.base.BaseActivity;
import com.meikoz.core.util.StatusBarUtil;

import butterknife.Bind;

/**
 * @User: 蜡笔小新
 * @date: 16-12-9
 * @GitHub: https://github.com/meikoz
 */

public abstract class SplashActivity extends BaseActivity {

    @Bind(R.id.iv_splash_background)
    ImageView mImageView;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    protected abstract Class getNextActivity2Launch();

    @Override
    protected void onInitialization(Bundle bundle) {
        StatusBarUtil.setTranslucentBackground(this);

        ImageControl.getInstance()
                .loadNet(mImageView, "http://ww2.sinaimg.cn/large/610dc034jw1fak99uh554j20u00u0n09.jpg", R.anim.sacle_largen_view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getNextActivity2Launch() != null)
                    startNextActivity(getNextActivity2Launch());
            }
        }, 1500);
    }

    private void startNextActivity(final Class clazz) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, clazz));
                finish();
            }
        });
    }
}
