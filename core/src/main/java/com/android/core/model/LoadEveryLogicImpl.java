package com.android.core.model;

import com.android.core.base.BasePresenter;

import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-08-01 15:02
 * @GitHub: https://github.com/meikoz
 */
public class LoadEveryLogicImpl<T> extends BasePresenter<LoadEveryLogic.LoadEveryView> implements LoadEveryLogic<T> {

    @Override
    public void onLoadCompleteData(Response<T> response) {
        T body = response.body();
        if (body != null)
            getView().onLoadComplete(body);
    }

    @Override
    public void onFailer(String msg) {
        getView().hideProgress();
        getView().onLoadFailer(msg);
    }
}
