package com.zhoujinlong.presenter.core;

import com.android.core.model.control.BasePresenter;

import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-22 10:38
 * @GitHub: https://github.com/meikoz
 */
public class LoadPresenter<D> extends BasePresenter<LoadView> {

    public void onLoadSuccessResponse(Response<D> response) {
        getView().onLoadComplete();
        D body = response.body();
        if (body != null)
            getView().onLoadSuccessResponse(body);
    }

    public void onLoadFail(String msg) {
        //可以选择单独还是统一处理
        getView().onFailure(msg);
    }
}
