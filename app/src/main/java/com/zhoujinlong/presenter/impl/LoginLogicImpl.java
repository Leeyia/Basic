package com.zhoujinlong.presenter.impl;

import com.zhoujinlong.model.http.BaseHttpService;
import com.zhoujinlong.model.http.Factory;
import com.zhoujinlong.presenter.LoginLogic;
import com.zhoujinlong.presenter.view.LoginView;

/**
 * author meikoz on 2016/4/13.
 * email  meikoz@126.com
 */
public class LoginLogicImpl implements LoginLogic {

    BaseHttpService mainService;
    LoginView mView;

    @Override
    public void login(String name, String passwrod) {
        if (name.equals("zhangsan") && passwrod.equals("123"))
            mView.onLoginSuccess();
        else
            mView.onLoginFail();
    }

    @Override
    public void attachView(LoginView mvpView) {
        this.mView = mvpView;
        this.mainService = Factory.provideHttpService();
    }

}
