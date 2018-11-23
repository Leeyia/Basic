package com.racofix.basic.mvp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.ref.WeakReference;

public class BaseFragment<T extends BaseLogic> extends Fragment implements BaseLogic.Vo {

    private WeakReference<T> mLogicWrf;

    private T initLogic() {
        return (T) LogicProviders.init(getClass());
    }

    protected T getLogic() {
        return this.mLogicWrf.get();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (initLogic() == null) return;

        LogicViewModel<T> viewModel = ViewModelProviders.of(this).get(LogicViewModel.class);
        if (viewModel.getLogicImpl() == null) {
            viewModel.setLogicImpl(initLogic());
            viewModel.getLogicImpl().onLogicCreated();
        }

        this.mLogicWrf = new WeakReference<>(viewModel.getLogicImpl());
        if (this.mLogicWrf.get() != null) {
            this.mLogicWrf.get().attachLifecycle(this.getLifecycle());
            this.mLogicWrf.get().attachVo(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mLogicWrf != null && this.mLogicWrf.get() != null && this.mLogicWrf.get().isVoAttached()) {
            this.mLogicWrf.get().detachLifecycle(getLifecycle());
            this.mLogicWrf.get().detachVo();
        }
    }
}
