package com.matto.ui.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.common.view.base.BaseActivity;
import com.common.utils.StatusBarUtil;
import com.matto.R;
import com.matto.util.AnimationUtil;

import butterknife.Bind;

/**
 * author meikoz on 2016/3/30.
 * email  meikoz@126.com
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.splash_view)
    ImageView splashView;
    long milliseconds = 1500;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_spalsh;
    }

    @Override
    protected void onInitView() {
        //设置状态栏透明
        StatusBarUtil.setTranslucentBackground(this);
        //开始执行动画,开始跳转
        startScaleAnimation();
    }

    private void startScaleAnimation() {
        /** 设置位移动画 向右位移150 */
        ScaleAnimation animation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(milliseconds);//设置动画持续时间
        animation.setFillAfter(true);
        splashView.setAnimation(animation);
        animation.startNow();
        AnimationUtil.setAnimationListener(animation, new AnimationUtil.AnimListener() {
            @Override
            public void onAnimFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
//                LoginActivity.start(SplashActivity.this);
            }
        });
    }

}
