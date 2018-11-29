package com.racofix.basic.mvp;

import android.arch.lifecycle.ViewModel;

public final class LogicViewModel<T extends LogicI> extends ViewModel {

    private T mLogicImpl;

    void setLogicImpl(T mLogic) {
        if (this.mLogicImpl == null && mLogic != null) {
            this.mLogicImpl = mLogic;
        }
    }

    T getLogicImpl() {
        return this.mLogicImpl;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (this.mLogicImpl != null) {
            this.mLogicImpl.onLogicDestroy();
            this.mLogicImpl = null;
        }
    }
}
