package com.zhoujinlong.presenter;

import com.zhoujinlong.presenter.core.LoadPresenter;
import com.zhoujinlong.model.bean.Classify;
import com.zhoujinlong.model.http.Factory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author meikoz on 2016/4/13.
 * email  meikoz@126.com
 */
public class LoginLogic extends LoadPresenter implements ILogin {
    @Override
    public void login(String name, String passwrod) {
        Factory.provideHttpService().getImageClassify(1).enqueue(new Callback<Classify>() {
            @Override
            public void onResponse(Call<Classify> call, Response<Classify> response) {
                onLoadSuccessResponse(response);
            }

            @Override
            public void onFailure(Call<Classify> call, Throwable t) {
                onLoadFail(t.getMessage().toString());
            }
        });
    }
}
