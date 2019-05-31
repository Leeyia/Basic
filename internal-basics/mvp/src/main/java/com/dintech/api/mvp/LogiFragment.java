package com.dintech.api.mvp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

public class LogiFragment<T extends BaseLogi> extends Fragment {

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
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
    public void onDestroyView() {
        super.onDestroyView();
        if (checkLogicNonNull()) {
            getLogiImpl().unbindLifecycle(this.getLifecycle());
            getLogiImpl().unbindView();
        }
    }
}
