package com.racofix.basic.mvp;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;

public interface BaseLogic<V extends BaseLogic.Vo> {

    Bundle getStateBundle();

    void attachLifecycle(Lifecycle lifecycle);

    void detachLifecycle(Lifecycle lifecycle);

    void attachVo(V vo);

    void detachVo();

    V getVo();

    boolean isVoAttached();

    void onLogicCreated();

    void onLogicDestroy();

    /*Base View*/
    interface Vo {

    }
}
