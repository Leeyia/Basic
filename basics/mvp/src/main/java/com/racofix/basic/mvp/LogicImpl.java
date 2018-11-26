package com.racofix.basic.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class LogicImpl<V extends LogicI.Vo> implements LogicI<V>, LifecycleObserver {

    private Bundle stateBundle;
    private WeakReference<V> wrf;

    @Override
    public Bundle getStateBundle() {
        return stateBundle == null
                ? stateBundle = new Bundle()
                : stateBundle;
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
    final public void bindVo(V vo) {
        this.wrf = new WeakReference<>(vo);
    }

    @Override
    final public void unbindVo() {
        this.wrf.clear();
        this.wrf = null;
    }

    @Override
    final public boolean isVoBind() {
        return this.wrf != null && this.wrf.get() != null;
    }

    @Override
    final public V getVo() {
        return isVoBind() ? wrf.get() : null;
    }


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
