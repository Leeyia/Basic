package com.android.core.base.rx;

/**
 * @User: 蜡笔小新
 * @date: 16-9-30
 * @GitHub: https://github.com/meikoz
 */
public interface ApiCallback<T> {

    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();
}
