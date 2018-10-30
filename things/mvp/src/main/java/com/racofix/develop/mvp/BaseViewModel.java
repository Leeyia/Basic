package com.racofix.develop.mvp;

import android.arch.lifecycle.ViewModel;

public final class BaseViewModel<T extends LogicI> extends ViewModel {

    private T mLogic;

    void setLogicImpl(T mLogicImpl) {
        if (BaseViewModel.this.mLogic == null)
            BaseViewModel.this.mLogic = mLogicImpl;
    }

    T getLogicImpl() {
        return BaseViewModel.this.mLogic;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (BaseViewModel.this.mLogic != null) {
            BaseViewModel.this.mLogic.onLogicDestroy();
            BaseViewModel.this.mLogic = null;
        }
    }
}
