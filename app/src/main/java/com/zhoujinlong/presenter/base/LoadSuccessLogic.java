package com.zhoujinlong.presenter.base;

import com.android.core.model.control.BaseLogic;

import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:39
 * @GitHub: https://github.com/meikoz
 */
public interface LoadSuccessLogic<T> extends BaseLogic<CommonView<T>> {

    void onLoadListSuccessHandle(Response<T> response, boolean isMore);

    void onLoadFail(String msg);
}
