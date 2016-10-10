package com.racofix.basic.presenter;

import com.android.core.base.BaseView;
import com.android.core.model.annotation.Implement;

/**
 * @User: 蜡笔小新
 * @date: 16-10-10
 * @GitHub: https://github.com/meikoz
 */
@Implement(LoginLogicImpl.class)
public interface LoginLogicI {
    void userLogin(String username, String password);

    interface LoginView extends BaseView {
        void loginSuccess();

        void loginFail();
    }
}
