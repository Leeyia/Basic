package com.meikoz.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.meikoz.core.manage.log.Logger;
import com.meikoz.core.model.LogicProxy;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected BasePresenter mPresenter;

    protected abstract int getLayoutResource();

    protected abstract void onInitialization(Bundle bundle);

    protected Class getLogicClazz() {
        return null;
    }

    protected void onInitData2Remote() {
        if (getLogicClazz() != null)
            mPresenter = getLogicImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("name (%s.java:0)", getClass().getSimpleName());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getLayoutResource() != 0)
            setContentView(getLayoutResource());
        ButterKnife.bind(this);
        this.onInitialization(savedInstanceState);
        this.onInitData2Remote();
    }

    //获得该页面的实例
    public <T> T getLogicImpl() {
        return LogicProxy.getInstance().bind(getLogicClazz(), this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null && !mPresenter.isViewBind()) {
            LogicProxy.getInstance().bind(getLogicClazz(), this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        LogicProxy.getInstance().unbind(getLogicClazz(), this);
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
