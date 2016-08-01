package com.android.core.model;

import com.android.core.base.BasePresenter;

import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-08-01 15:32
 * @GitHub: https://github.com/meikoz
 */
public class LoadListDataLogicImpl<T> extends BasePresenter<LoadListDataLogic.LoadListView> implements LoadListDataLogic<T> {
    @Override
    public void onLoadComplete(Response<T> response, boolean isMore) {
        getView().onLoadComplete(isMore);
        T body = response.body();
        if (body != null)
            getView().onLoadCompleteData(body, isMore);
    }

    @Override
    public void onFailer(String msg) {
        getView().showErrorMessage("网络错误", msg);
    }
}
