package com.racofix.things2.example.mvp;

public interface LoginI {

    interface Logic {
        void sign_in(String username, String password);
    }

    interface View {
        void sign_in_success();
    }
}
