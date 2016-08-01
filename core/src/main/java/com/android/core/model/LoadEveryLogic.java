package com.android.core.model;

import com.android.core.base.BaseView;

import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-08-01 15:03
 * @GitHub: https://github.com/meikoz
 */
public interface LoadEveryLogic<T> {

    void onLoadCompleteData(Response<T> response);

    void onFailer(String msg);

    interface LoadEveryView<T> extends BaseView {
        void onLoadComplete(T body);

        void onLoadFailer(String msg);
    }
}
