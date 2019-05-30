package com.dintech.api.mvp;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;

interface BaseLogi<V> {

    Bundle getStateBundle();

    void bindLifecycle(Lifecycle lifecycle);

    void unbindLifecycle(Lifecycle lifecycle);

    void bindView(V view);

    void unbindView();

    V getView();

    boolean isViewBind();

    void onCreated();

    void onDestroy();
}
