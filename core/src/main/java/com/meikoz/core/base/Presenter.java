package com.meikoz.core.base;

public interface Presenter<V> {
    void attachView(V mvpView);
    void detachView();
}
