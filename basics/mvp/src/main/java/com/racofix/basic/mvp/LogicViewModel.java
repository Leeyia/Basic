package com.racofix.basic.mvp;

import android.arch.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

public final class LogicViewModel<T extends BaseLogic> extends ViewModel {

    private WeakReference<T> mLogicWrf;

    void setLogicImpl(T mLogicImpl) {
        if (this.mLogicWrf == null || this.mLogicWrf.get() == null)
            this.mLogicWrf = new WeakReference<>(mLogicImpl);
    }

    T  getLogicImpl() {
        return this.mLogicWrf.get();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (this.mLogicWrf != null) {
            this.mLogicWrf.get().onLogicDestroy();
            this.mLogicWrf.clear();
            this.mLogicWrf = null;
        }
    }
}
