package com.android.core.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.android.core.manage.log.Logger;
import com.android.core.model.LogicProxy;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements BaseView {

    protected abstract int getLayoutResource();

    protected abstract void onInitView(Bundle savedInstanceState);

    protected abstract Class getLogic();

    protected void onInitData2Api() {
        if (getLogic() != null)
            mPresenter = getLogicImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutResource() != 0) {
            mContentView = inflater.inflate(getLayoutResource(), null);
        } else {
            mContentView = super.onCreateView(inflater, container, savedInstanceState);
        }
        this.onInitView(savedInstanceState);
        return mContentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("name (%s.java:0)", getClass().getSimpleName());
        mContext = getActivity();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        onInitData2Api();
    }

    //获得该页面的实例
    public <T> T getLogicImpl() {
        return LogicProxy.getInstance().bind(getLogic(), this);
    }


    @Override
    public void onStart() {
        if (mPresenter != null && !mPresenter.isViewBind()) {
            LogicProxy.getInstance().bind(getLogic(), this);
        }
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mPresenter != null)
            mPresenter.detachView();
    }

    protected BasePresenter mPresenter;
    protected View mContentView;
    protected Context mContext = null;//context
}
