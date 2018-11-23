package com.racofix.basic.mvp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public abstract class LogicAct<T extends LogicI> extends FragmentActivity implements LogicVo {

    protected T mLogicImpl;

    private T getLogicIml() {
        return (T) LogicProviders.init(getClass());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseViewModel<T> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        if (getLogicIml() != null) {
            if (viewModel.getLogicImpl() == null) {
                viewModel.setLogicImpl(getLogicIml());
                viewModel.getLogicImpl().onLogicCreated();
            }

            mLogicImpl = viewModel.getLogicImpl();
            if (mLogicImpl != null) {
                mLogicImpl.attachLifecycle(LogicAct.this.getLifecycle());
                mLogicImpl.attachVo(LogicAct.this);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLogicImpl != null) {
            mLogicImpl.detachLifecycle(getLifecycle());
            mLogicImpl.detachVo();
        }
    }
}
