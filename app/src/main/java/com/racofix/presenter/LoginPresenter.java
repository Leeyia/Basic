package com.racofix.presenter;

import com.android.core.model.LoadEveryLogicImpl;
import com.racofix.api.Factory;
import com.racofix.model.repo.Classify;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-08-01 15:15
 * @GitHub: https://github.com/meikoz
 */
public class LoginPresenter extends LoadEveryLogicImpl<Classify> implements LoginContract {

    @Override
    public void onLogin(String username, String password) {
        Factory.provideHttpService().getImageClassify(1).enqueue(new Callback<Classify>() {
            @Override
            public void onResponse(Call<Classify> call, Response<Classify> response) {
                onLoadCompleteData(response);
            }

            @Override
            public void onFailure(Call<Classify> call, Throwable t) {
                onFailer(t.getMessage());
            }
        });
    }
}
