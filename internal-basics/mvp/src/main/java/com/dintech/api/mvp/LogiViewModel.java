package com.dintech.api.mvp;

import android.arch.lifecycle.ViewModel;

public final class LogiViewModel<T extends BaseLogi> extends ViewModel {

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
