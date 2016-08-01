package com.android.core.base;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-21 17:42
 * @GitHub: https://github.com/meikoz
 */
public interface Presenter<V> {
    void attachView(V mvpView);
    void detachView();
}
