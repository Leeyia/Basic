package com.common.model.control;

/**
 * author meikoz on 2016/4/13.
 * email  meikoz@126.com
 */
public interface MvpLogic<T> {
    void attachView(T mvpView);
}
