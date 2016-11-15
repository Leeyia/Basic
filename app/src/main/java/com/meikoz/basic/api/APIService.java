package com.meikoz.basic.api;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @User: 蜡笔小新
 * @date: 16-11-15
 * @GitHub: https://github.com/meikoz
 */

public interface APIService {
    @GET("api/v1/user")
    Call<Response> getUserInfo(@Query("uid") int uid);
}
