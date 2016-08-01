package com.racofix.presenter;

import com.android.core.model.annotation.Implement;

/**
 * @author: 蜡笔小新
 * @date: 2016-08-01 15:13
 * @GitHub: https://github.com/meikoz
 */
@Implement(LoginPresenter.class)
public interface LoginContract {
    void onLogin(String username, String password);
}
