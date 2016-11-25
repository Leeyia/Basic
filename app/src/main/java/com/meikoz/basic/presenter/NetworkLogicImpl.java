package com.meikoz.basic.presenter;

import com.meikoz.basic.api.ApiInterface;
import com.meikoz.basic.bean.Gank;
import com.meikoz.core.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @User: 蜡笔小新
 * @date: 16-11-1
 * @GitHub: https://github.com/meikoz
 */

public class NetworkLogicImpl extends BasePresenter<NetworkLogicI.NetworkView> implements NetworkLogicI {

    @Override
    public void onLoadNetworkData(int size, final int page) {
        Callback callback = new Callback<Gank>() {
            @Override
            public void onResponse(Call<Gank> call, Response<Gank> response) {
                if (!response.body().isError())
                    getView().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Gank> call, Throwable t) {
                getView().onFailure(t.getMessage());
            }
        };
        ApiInterface.ApiFactory.createApi().onLoadNetworkData(size, page).enqueue(callback);
    }
}
