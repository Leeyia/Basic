package com.racofix.basic.mvp;

import android.arch.lifecycle.ViewModel;

public final class LogicViewModel<T extends BaseLogic> extends ViewModel {

    private T mLogicImpl;

    void setLogicImpl(T mLogicImpl) {
        if (this.mLogicImpl == null)
            this.mLogicImpl = mLogicImpl;
    }

    T getLogicImpl() {
        return this.mLogicImpl;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (this.mLogicImpl != null) {
            this.mLogicImpl = null;
        }
    }
}
