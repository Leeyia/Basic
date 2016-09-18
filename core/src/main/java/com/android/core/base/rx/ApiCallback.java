package com.android.core.base.rx;

/**
 * Created by zjl on 16-9-18.
 */
public interface ApiCallback<T> {

    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();
}
