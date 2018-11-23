package com.racofix.basic.things.ui;

import com.racofix.basic.mvp.BaseLogic;

public interface LoginContract {

    interface Logic {
        void login(String username, String password);
    }

    interface Vo extends BaseLogic.Vo {
        void successful(String message);

        void failed(String failMsg);
    }
}
