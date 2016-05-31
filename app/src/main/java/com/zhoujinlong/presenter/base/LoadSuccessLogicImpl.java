package com.zhoujinlong.presenter.base;

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
        view.onLoadComplete();
        T body = response.body();
        if (body != null) {
            view.onShowListData(body, isMore);
        }
//        else {
//            if (isMore) {
//                //加载更多 Toast "没有更多数据"
//            } else {
////                Toast "没有更多数据"
//            }
//        }
    }

    @Override
    public void onLoadFail(String msg) {
        //提示请求失败
    }

    @Override
    public void attachView(CommonView<T> v) {
        view = v;
    }
}
