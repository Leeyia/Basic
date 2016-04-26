package com.matto.model.http;


import com.matto.model.pojo.Gank;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public interface BaseHttpService {

    /**
     * 使用缓存机制
     */
    @Headers("Cache-Control: public, max-age=3600")
    @GET("data/Android/{size}/{page}")
    Call<Gank> getMainAndroid(
            @Path("size") int size,
            @Path("page") int page
    );

}
