package com.zhoujinlong.presenter.impl;

import com.zhoujinlong.model.bean.Classify;
import com.zhoujinlong.model.http.BaseHttpService;
import com.zhoujinlong.model.http.Factory;
import com.zhoujinlong.presenter.CompeteLogic;
import com.zhoujinlong.ui.view.CompeteView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author meikoz on 2016/4/27.
 * email  meikoz@126.com
 */
public class CompeteLogicImpl implements CompeteLogic {
    private CompeteView mView;
    private BaseHttpService service;


    @Override
    public void onRefreshData() {
        service.getImageClassify(1).enqueue(new Callback<Classify>() {
            @Override
            public void onResponse(Call<Classify> call, Response<Classify> response) {
                Classify body = response.body();
                if (body.isStatus())
                    mView.onSuccess(body.getTngou());
            }

            @Override
            public void onFailure(Call<Classify> call, Throwable t) {

            }
        });
    }

    @Override
    public void onLoadMoreData() {
        service.getImageClassify(2).enqueue(new Callback<Classify>() {
            @Override
            public void onResponse(Call<Classify> call, Response<Classify> response) {
                Classify body = response.body();
                if (body.isStatus())
                    mView.onSuccess(body.getTngou());
            }

            @Override
            public void onFailure(Call<Classify> call, Throwable t) {

            }
        });
    }

    @Override
    public void attachView(CompeteView mvpView) {
        this.mView = mvpView;
        service = Factory.provideHttpService();
    }
}
