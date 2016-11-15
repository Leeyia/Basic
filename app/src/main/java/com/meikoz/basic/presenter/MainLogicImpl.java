package com.meikoz.basic.presenter;

import com.meikoz.basic.api.ApiManager;
import com.meikoz.core.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @User: 蜡笔小新
 * @date: 16-11-1
 * @GitHub: https://github.com/meikoz
 */

public class MainLogicImpl extends BasePresenter<MainLogicI.MainView> implements MainLogicI {
    @Override
    public void onLoadData2Remote() {
        ApiManager.createApi().getUserInfo(1).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                getView().onLoadSuccessHandler("请求成功,返回数据");
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
