package com.meikoz.basic.api;

import com.meikoz.core.api.RestApi;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @User: 蜡笔小新
 * @date: 16-11-15
 * @GitHub: https://github.com/meikoz
 */

public interface ApiInterface {
    String BASE_URL = "http://www.tngou.net/";

    @GET("tnfs/api/list")
    Call<Response> getImageClassify(@Query("id") int id);

    class ApiFactory {
        static ApiInterface createApi() {
            return RestApi.getInstance().create(ApiInterface.class);
        }
    }
}
