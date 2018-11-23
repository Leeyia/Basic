package com.racofix.basic.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class LogicImpl<V extends LogicVo> implements LogicI<V>, LifecycleObserver {

    private Bundle stateBundle;
    private WeakReference<V> wrf;

    @Override
    public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    public void attachVo(V vo) {
        this.wrf = new WeakReference<>(vo);
    }

    @Override
    public void detachVo() {
        this.wrf.clear();
        this.wrf = null;
    }

    @Override
    public V getVo() {
        return isVoAttached() ? wrf.get() : null;
    }


    @Override
    public boolean isVoAttached() {
        return this.wrf != null && this.wrf.get() != null;
    }

    @Override
    public Bundle getStateBundle() {
        return stateBundle == null
                ? stateBundle = new Bundle()
                : stateBundle;
    }

    @Override
    public void onLogicCreated() {
        //LogicImpl create
    }

    @Override
    public void onLogicDestroy() {
        if (stateBundle != null && !stateBundle.isEmpty()) {
            stateBundle.clear();
        }
    }
}
