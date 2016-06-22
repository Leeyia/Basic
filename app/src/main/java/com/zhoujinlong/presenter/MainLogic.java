package com.zhoujinlong.presenter;

import com.zhoujinlong.model.bean.Classify;
import com.zhoujinlong.model.http.Factory;
import com.zhoujinlong.presenter.core.LoadListDataLogicImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 12:05
 * @GitHub: https://github.com/meikoz
 */
public class MainLogic extends LoadListDataLogicImpl implements IMain {
    @Override
    public void onLoadRemoteData(final boolean isMore, int page) {
        Factory.provideHttpService().getImageClassify(page).enqueue(new Callback<Classify>() {
            @Override
            public void onResponse(Call<Classify> call, Response<Classify> response) {
                onLoadListSuccessHandle(response, isMore);
            }

            @Override
            public void onFailure(Call<Classify> call, Throwable t) {
                onLoadFail("wangluocuwu");
            }
        });
    }
}
