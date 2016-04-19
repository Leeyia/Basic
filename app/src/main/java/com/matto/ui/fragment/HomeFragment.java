package com.matto.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.common.view.base.BaseFragment;
import com.common.view.widget.OnClickEvent;
import com.matto.R;
import com.matto.ui.activity.AndroidSwipActivity;
import com.matto.ui.activity.LoginActivity;

import butterknife.Bind;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.splash_view)
    ImageView splashView;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_spalsh;
    }

    @Override
    protected void onInitData() {
        splashView.setOnClickListener(new OnClickEvent() {
            @Override
            public void singleClick(View v) {
                startActivity(new Intent(getActivity(), AndroidSwipActivity.class));
            }
        });
    }
}
