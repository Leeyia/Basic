package com.racofix.basic.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class BaseLogicImpl<V extends BaseLogic.Vo> implements BaseLogic<V>, LifecycleObserver {

    private Bundle stateBundle;
    private WeakReference<V> wrf;

    @Override
    public Bundle getStateBundle() {
        return stateBundle == null
                ? stateBundle = new Bundle()
                : stateBundle;
    }

    @Override
    final public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    final public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    final public void attachVo(V vo) {
        this.wrf = new WeakReference<>(vo);
    }

    @Override
    final public void detachVo() {
        this.wrf.clear();
        this.wrf = null;
    }

    @Override
    final public boolean isVoAttached() {
        return this.wrf != null && this.wrf.get() != null;
    }

    @Override
    final public V getVo() {
        return isVoAttached() ? wrf.get() : null;
    }

    /*Logic Created */
    @Override
    public void onLogicCreated() {

    }

    @Override
    public void onLogicDestroy() {
        if (stateBundle != null && !stateBundle.isEmpty()) {
            stateBundle.clear();
        }
    }
}
