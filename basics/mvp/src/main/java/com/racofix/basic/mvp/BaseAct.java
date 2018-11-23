package com.racofix.basic.mvp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

public class BaseAct<T extends BaseLogic> extends FragmentActivity implements BaseLogic.Vo {

    private WeakReference<T> mLogicWrf;

    private T initLogic() {
        return (T) LogicProviders.init(this.getClass());
    }

    protected T getLogic() {
        return this.mLogicWrf.get();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (initLogic() == null) return;

        LogicViewModel<T> viewModel = ViewModelProviders.of(this).get(LogicViewModel.class);
        if (viewModel.getLogicImpl() == null) {
            viewModel.setLogicImpl(initLogic());
            viewModel.getLogicImpl().onLogicCreated();
        }

        this.mLogicWrf = new WeakReference<>(viewModel.getLogicImpl());
        if (this.mLogicWrf.get() != null) {
            this.mLogicWrf.get().attachLifecycle(BaseAct.this.getLifecycle());
            this.mLogicWrf.get().attachVo(BaseAct.this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mLogicWrf != null
                && this.mLogicWrf.get() != null
                && this.mLogicWrf.get().isVoAttached()) {
            this.mLogicWrf.get().detachLifecycle(getLifecycle());
            this.mLogicWrf.get().detachVo();
        }
    }
}
