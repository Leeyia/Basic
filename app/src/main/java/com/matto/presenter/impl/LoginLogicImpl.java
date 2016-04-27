package com.matto.presenter.impl;

import android.util.Log;

import com.matto.model.http.BaseHttpService;
import com.matto.model.http.Factory;
import com.matto.model.bean.Gank;
import com.matto.presenter.LoginLogic;
import com.matto.ui.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author meikoz on 2016/4/13.
 * email  meikoz@126.com
 */
public class LoginLogicImpl implements LoginLogic {

    BaseHttpService mainService;
    LoginView mView;

    @Override
    public void login(String name, String passwrod) {

    }

    @Override
    public void attachView(LoginView mvpView) {
        this.mView = mvpView;
        this.mainService = Factory.provideHttpService();
    }

}
