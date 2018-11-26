package com.racofix.basic.mvp;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;

public interface LogicI<V extends LogicI.Vo> {

    Bundle getStateBundle();

    void bindLifecycle(Lifecycle lifecycle);

    void unbindLifecycle(Lifecycle lifecycle);

    void bindVo(V vo);

    void unbindVo();

    V getVo();

    boolean isVoBind();

    void onLogicCreated();

    void onLogicDestroy();

    /*Base View*/
    interface Vo {

    }
}
