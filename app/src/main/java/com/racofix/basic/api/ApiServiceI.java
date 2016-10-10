package com.racofix.basic.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @User: 蜡笔小新
 * @date: 16-10-10
 * @GitHub: https://github.com/meikoz
 */

public interface ApiServiceI {

    @GET("user/login")
    Call<String> onUserLogin(String username, String password);
}
