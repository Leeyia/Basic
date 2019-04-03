package com.dintech.android.mvp;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

public final class AndroidViewModel<T extends Logic> extends ViewModel {

    public static AndroidViewModel of(FragmentActivity activity){
        return ViewModelProviders.of(activity).get(AndroidViewModel.class);
    }

    private T mLogicImpl;

    void setLogicImpl(T mLogic) {
        if (this.mLogicImpl == null && mLogic != null) {
            this.mLogicImpl = mLogic;
        }
    }

    T getPresenterImpl() {
        return this.mLogicImpl;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (this.mLogicImpl != null) {
            this.mLogicImpl.onDestroy();
            this.mLogicImpl = null;
        }
    }
}
