package com.racofix.presenter.core;

import com.android.core.control.Toast;
import com.android.core.model.mvp.BasePresenter;

import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 11:51
 * @GitHub: https://github.com/meikoz
 */
public class LoadListDataLogicImpl<T> extends BasePresenter<BaseListView> implements LoadListDataLogic<T> {

//    BaseListView view;

    /**
     * 处理获取列表成功回调的公共函数
     *
     * @param response
     * @param isMore
     */
    public void onLoadListSuccessHandle(Response<T> response, boolean isMore) {
        //加载完成
        getView().onLoadComplete(isMore);
        T body = response.body();
        if (body != null) {
            getView().onResponseLData(body, isMore);
        }
    }

    @Override
    public void onLoadFail(String msg) {
        Toast.show(msg);
        //提示请求失败
    }

}
