package com.racofix.develop.things.ui;

import com.racofix.develop.mvp.Vo;

public interface HttpVo<M> extends Vo {

    void successful(M m);

    void failed(String message);
}
