package com.zhoujinlong.presenter;

import com.android.core.model.annotation.Implement;
import com.android.core.model.control.BaseLogic;
import com.zhoujinlong.presenter.impl.LoginLogicImpl;
import com.zhoujinlong.presenter.view.LoginView;


@Implement(LoginLogicImpl.class)
public interface LoginLogic extends BaseLogic<LoginView> {

    void login(String name, String passwrod);
}