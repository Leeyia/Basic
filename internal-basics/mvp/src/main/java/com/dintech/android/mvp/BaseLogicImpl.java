package com.dintech.android.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class BaseLogicImpl<V extends View> implements Logic<V>, LifecycleObserver {

    private Bundle stateBundle;
    private WeakReference<V> wrf;

    @Override
    public Bundle getStateBundle() {
        return stateBundle == null ?
                stateBundle = new Bundle() : stateBundle;
    }

    @Override
    final public void bindLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    final public void unbindLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    final public void bindView(V vo) {
        this.wrf = new WeakReference<>(vo);
    }

    @Override
    final public void unbindView() {
        this.wrf.clear();
        this.wrf = null;
    }

    @Override
    final public boolean isViewBind() {
        return this.wrf != null && this.wrf.get() != null;
    }

    @Override
    final public V getView() {
        return isViewBind() ? wrf.get() : null;
    }

    @Override
    public void onCreated() {
    }

    @Override
    public void onDestroy() {
        if (stateBundle != null && !stateBundle.isEmpty()) {
            stateBundle.clear();
        }
    }
}
