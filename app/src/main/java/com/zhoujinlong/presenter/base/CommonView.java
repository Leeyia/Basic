package com.zhoujinlong.presenter.base;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 11:47
 * @GitHub: https://github.com/meikoz
 */
public interface CommonView<T> {

    void onLoadComplete();

    void onShowListData(T response, boolean isMore);
}
