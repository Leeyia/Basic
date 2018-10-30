package com.racofix.develop.mvp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity<T extends LogicI> extends FragmentActivity implements Vo {

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
            mLogicImpl.attachLifecycle(BaseActivity.this.getLifecycle());
            mLogicImpl.attachVo(BaseActivity.this);
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
