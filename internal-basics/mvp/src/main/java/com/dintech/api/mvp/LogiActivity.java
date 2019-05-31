package com.dintech.api.mvp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public abstract class LogiActivity<T extends BaseLogi> extends AppCompatActivity {

    private WeakReference<T> mLogicWrf;

    protected T getLogiImpl() {
        return this.mLogicWrf.get();
    }

    private T providerLogic() {
        return (T) LogiProviders.init(this.getClass());
    }

    private boolean checkLogicNonNull() {
        return this.mLogicWrf != null && this.mLogicWrf.get() != null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (providerLogic() == null) return;

        LogiViewModel<T> viewModel = ViewModelProviders.of(this).get(LogiViewModel.class);
        boolean isLogicCreated = false;
        if (viewModel.getLogiImpl() == null) {
            viewModel.setLogicImpl(providerLogic());
            isLogicCreated = true;
        }

        this.mLogicWrf = new WeakReference<>(viewModel.getLogiImpl());
        if (checkLogicNonNull()) {
            getLogiImpl().bindLifecycle(this.getLifecycle());
            getLogiImpl().bindView(this);
            if (isLogicCreated) getLogiImpl().onCreated();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (checkLogicNonNull()) {
            getLogiImpl().unbindLifecycle(this.getLifecycle());
            getLogiImpl().unbindView();
        }
    }
}
