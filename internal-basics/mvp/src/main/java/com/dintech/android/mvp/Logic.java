package com.dintech.android.mvp;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;

public interface Logic<V extends View> {

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
