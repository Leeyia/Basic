package com.racofix.basic.mvp;

public interface HttpVo<M> extends Vo {

    void onResponse(M m);

    void onFailure(String message);
}
