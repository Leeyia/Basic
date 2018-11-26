package com.racofix.basic.mvp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

public class BaseLogicActivity<T extends LogicI> extends FragmentActivity implements LogicI.Vo {

    private WeakReference<T> mLogicWrf;

    protected T getLogicImpl() {
        return this.mLogicWrf.get();
    }

    private T providerLogic() {
        return (T) LogicProviders.init(this.getClass());
    }

    private boolean checkLogicNonNull() {
        return this.mLogicWrf == null || this.mLogicWrf.get() == null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (providerLogic() == null) return;

        LogicViewModel<T> viewModel = ViewModelProviders.of(this).get(LogicViewModel.class);
        if (viewModel.getLogicImpl() == null) {
            viewModel.setLogicImpl(providerLogic());
        }

        this.mLogicWrf = new WeakReference<>(viewModel.getLogicImpl());
        if (checkLogicNonNull()) {
            getLogicImpl().bindLifecycle(this.getLifecycle());
            getLogicImpl().bindView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (checkLogicNonNull()) {
            getLogicImpl().unbindLifecycle(this.getLifecycle());
            getLogicImpl().unbindView();
        }
    }
}
