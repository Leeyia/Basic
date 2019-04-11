package com.dintech.api.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

public class BaseActivity<T extends Logic> extends FragmentActivity implements View {

    private WeakReference<T> mLogicWrf;

    protected T getLogicImpl() {
        return this.mLogicWrf.get();
    }

    private T providerLogic() {
        return (T) LogicProviders.init(this.getClass());
    }

    private boolean checkLogicNonNull() {
        return this.mLogicWrf != null && this.mLogicWrf.get() != null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (providerLogic() == null) return;

        AndroidViewModel<T> viewModel = AndroidViewModel.of(this);
        boolean isLogicCreated = false;
        if (viewModel.getPresenterImpl() == null) {
            viewModel.setLogicImpl(providerLogic());
            isLogicCreated = true;
        }

        this.mLogicWrf = new WeakReference<>(viewModel.getPresenterImpl());
        if (checkLogicNonNull()) {
            getLogicImpl().bindLifecycle(this.getLifecycle());
            getLogicImpl().bindView(this);
            if (isLogicCreated) getLogicImpl().onCreated();
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
