package com.meikoz.basic.app;

import com.meikoz.basic.model.Gank;
import com.meikoz.core.api.RestApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @User: 蜡笔小新
 * @date: 16-11-15
 * @GitHub: https://github.com/meikoz
 */

public interface ApiInterface {

    String BASE_URL = "http://gank.io/api/";

    @GET("data/福利/{size}/{page}")
    Call<Gank> onLoadNetworkData(@Path("size") int size, @Path("page") int page);

    class ApiFactory {
        public static ApiInterface createApi() {
            return RestApi.getInstance().create(ApiInterface.class);
        }
    }
}
