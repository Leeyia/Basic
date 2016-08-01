package com.racofix.presenter;

import com.android.core.model.LoadListDataLogicImpl;
import com.racofix.api.Factory;
import com.racofix.model.repo.Classify;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 12:05
 * @GitHub: https://github.com/meikoz
 */
public class MainPresenter extends LoadListDataLogicImpl<Classify> implements MainContract {
    @Override
    public void onLoadRemoteData(final boolean isMore, int page) {
        Factory.provideHttpService().getImageClassify(page).enqueue(new Callback<Classify>() {
            @Override
            public void onResponse(Call<Classify> call, Response<Classify> response) {
                onLoadComplete(response, isMore);
            }

            @Override
            public void onFailure(Call<Classify> call, Throwable t) {
                onFailer(t.getLocalizedMessage());
            }
        });
    }
}
