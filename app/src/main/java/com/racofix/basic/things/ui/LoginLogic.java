package com.racofix.basic.things.ui;

import com.racofix.basic.mvp.LogicI;

public interface LoginLogic {

    interface Logic {
        void login(String username, String password);
    }

    interface Vo extends LogicI.Vo {
        void successful(String msg);

        void failed(String failMsg);
    }
}
