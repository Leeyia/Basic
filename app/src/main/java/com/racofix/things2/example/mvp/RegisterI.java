package com.racofix.things2.example.mvp;

public interface RegisterI {

    interface Logic {
        void sign_up(String username);
    }

    interface View {
        void sign_up_success();
    }
}
