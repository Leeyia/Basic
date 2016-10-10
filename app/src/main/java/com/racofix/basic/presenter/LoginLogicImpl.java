package com.racofix.basic.presenter;

import com.android.core.base.BasePresenter;
import com.racofix.basic.api.ApiFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @User: 蜡笔小新
 * @date: 16-10-10
 * @GitHub: https://github.com/meikoz
 */

public class LoginLogicImpl extends BasePresenter<LoginLogicI.LoginView> implements LoginLogicI {

    @Override
    public void userLogin(String username, String password) {
        if (username.equals("zhangsan") && password.equals("123456")) {
            getView().loginSuccess();
        } else
            getView().loginFail();

//        Callback<String> callback = new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        };
//        ApiFactory.createApi().onUserLogin(username, password).enqueue(callback);
    }
}
