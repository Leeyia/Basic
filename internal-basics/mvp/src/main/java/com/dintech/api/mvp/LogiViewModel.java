package com.dintech.api.mvp;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

final class LogiViewModel<T extends BaseLogi> extends ViewModel {

    public static LogiViewModel of(FragmentActivity activity) {
        return ViewModelProviders.of(activity).get(LogiViewModel.class);
    }

    private T mLogiImpl;

    void setLogicImpl(T mLogic) {
        if (this.mLogiImpl == null && mLogic != null) {
            this.mLogiImpl = mLogic;
        }
    }

    T getLogiImpl() {
        return this.mLogiImpl;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (this.mLogiImpl != null) {
            this.mLogiImpl.onDestroy();
            this.mLogiImpl = null;
        }
    }
}
