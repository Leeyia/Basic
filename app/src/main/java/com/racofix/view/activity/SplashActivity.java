package com.racofix.view.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.android.core.control.Glides;
import com.android.core.control.HandlerTip;
import com.android.core.base.AbsBaseActivity;
import com.android.core.control.StatusBarUtil;
import com.racofix.R;

import butterknife.Bind;

/**
 * author meikoz on 2016/3/30.
 * email  meikoz@126.com
 */
public class SplashActivity extends AbsBaseActivity {

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
        ScaleAnimation animation = new ScaleAnimation(1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(milliseconds);//设置动画持续时间
        animation.setFillAfter(true);
        splashView.setAnimation(animation);
        Glides.getInstance().loadAnima(this, R.drawable.splash_background, animation, splashView);
        HandlerTip.getInstance().postDelayed(milliseconds, new HandlerTip.HandlerCallback() {
            @Override
            public void postDelayed() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
