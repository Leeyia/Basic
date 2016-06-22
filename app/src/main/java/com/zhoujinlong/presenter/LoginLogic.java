package com.zhoujinlong.presenter;

import com.android.core.model.annotation.Implement;


@Implement(LoginLogicImpl.class)
public interface LoginLogic {

    void login(String name, String passwrod);
}