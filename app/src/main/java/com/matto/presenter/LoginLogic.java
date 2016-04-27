package com.matto.presenter;

import com.common.model.control.BaseLogic;
import com.common.model.annotation.Implement;
import com.matto.presenter.impl.LoginLogicImpl;
import com.matto.ui.view.LoginView;


/**
 * author meikoz on 2016/4/13.
 * email  meikoz@126.com
 */

@Implement(LoginLogicImpl.class)
public interface LoginLogic extends BaseLogic<LoginView> {

    void login(String name, String passwrod);
}