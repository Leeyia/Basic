package com.racofix.presenter;

import com.android.core.model.annotation.Implement;


@Implement(LoginLogic.class)
public interface ILogin {

    void login(String name, String passwrod);
}