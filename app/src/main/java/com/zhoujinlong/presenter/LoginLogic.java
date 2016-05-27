package com.zhoujinlong.presenter;

import com.android.core.model.annotation.Implement;
import com.android.core.model.control.BaseLogic;
import com.zhoujinlong.presenter.impl.LoginLogicImpl;
import com.zhoujinlong.ui.view.LoginView;


/**
 * author meikoz on 2016/4/13.
 * email  meikoz@126.com
 */

@Implement(LoginLogicImpl.class)
public interface LoginLogic extends BaseLogic<LoginView> {

    void login(String name, String passwrod);
}