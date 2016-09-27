package com.racofix.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import com.android.core.base.BaseActivity;
import com.android.core.control.StatusBarUtil;
import com.android.core.control.image.ImageLoader;
import com.android.core.control.image.ImageLoaderProxy;
import com.racofix.R;

import butterknife.Bind;

/**
 * author meikoz on 2016/3/30.
 * email  meikoz@126.com
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.splash_view)
    ImageView splashView;
    int milliseconds = 1500;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_spalsh;
    }

    @Override
    protected void onInitView() {
        //设置状态栏透明
        StatusBarUtil.setTranslucentBackground(this);

        ImageLoaderProxy.getInstance().animate(this,
                new ImageLoader.Builder()
                        .load(R.drawable.splash_background)
                        .animate(R.anim.sacle_largen_view)
                        .into(splashView)
                        .build());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, milliseconds);
    }
}
