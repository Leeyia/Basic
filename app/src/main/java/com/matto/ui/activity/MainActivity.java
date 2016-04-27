package com.matto.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.common.model.control.LogicProxy;
import com.common.view.base.BaseActivity;
import com.matto.R;
import com.matto.presenter.MainLogic;
import com.matto.ui.fragment.CompeteFragment;
import com.matto.ui.fragment.DiscoveryFragment;
import com.matto.ui.fragment.UserFragment;
import com.matto.ui.view.MainView;

import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    MainLogic mainLogic;

    public static void start(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        context.finish();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {
        mainLogic = LogicProxy.getInstance().bind(MainLogic.class, this);
        switchCompete();
    }

    @Override
    public void switchCompete() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new CompeteFragment()).commit();
    }

    @Override
    public void switchDiscovery() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new DiscoveryFragment()).commit();
    }

    @Override
    public void switchAbout() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new UserFragment()).commit();
    }

    @OnClick({R.id.navigation_selection, R.id.navigation_discovery, R.id.navigation_about})
    void onClick(View view) {
        mainLogic.switchNavigation(view.getId());
    }
}
