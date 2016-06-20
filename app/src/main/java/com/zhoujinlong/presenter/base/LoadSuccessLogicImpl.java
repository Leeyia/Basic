package com.zhoujinlong.presenter.base;

import com.android.core.control.Toast;

import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 11:51
 * @GitHub: https://github.com/meikoz
 */
public class LoadSuccessLogicImpl<T> implements LoadSuccessLogic<T> {

    CommonView view;

    /**
     * 处理获取列表成功回调的公共函数
     *
     * @param response
     * @param isMore
     */
    public void onLoadListSuccessHandle(Response<T> response, boolean isMore) {
        //加载完成
        view.onLoadComplete();
        T body = response.body();
        if (body != null) {
            view.onShowListData(body, isMore);
        }
    }

    @Override
    public void onLoadFail(String msg) {
        Toast.show(msg);
        //提示请求失败
    }

    @Override
    public void attachView(CommonView<T> v) {
        view = v;
    }
}
