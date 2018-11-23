package com.racofix.basic.mvp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class LogicFragment<T extends LogicI> extends Fragment implements LogicVo {

    protected T mLogicImpl;

    private T getLogicIml() {
        return (T) LogicProviders.init(getClass());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BaseViewModel<T> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        if (getLogicIml() != null) {
            if (viewModel.getLogicImpl() == null) {
                viewModel.setLogicImpl(getLogicIml());
                viewModel.getLogicImpl().onLogicCreated();
            }

            mLogicImpl = viewModel.getLogicImpl();
            if (mLogicImpl != null) {
                mLogicImpl.attachLifecycle(LogicFragment.this.getLifecycle());
                mLogicImpl.attachVo(LogicFragment.this);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLogicImpl != null) {
            mLogicImpl.detachLifecycle(getLifecycle());
            mLogicImpl.detachVo();
        }
    }
}
