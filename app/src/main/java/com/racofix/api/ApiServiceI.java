package com.racofix.api;

import com.racofix.model.repo.Classify;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:15
 * @GitHub: https://github.com/meikoz
 */
public interface ApiServiceI {

    String BASE_URL = "http://www.tngou.net/";

    @GET("tnfs/api/list")
    Call<Classify> getImageClassify(@Query("id") int id);

    @GET("tnfs/api/list")
    Observable<Classify> onHomeData2Api(@Query("id") int id);

    @GET("tnfs/api/list")
    Observable<Classify> onHome2Api(@Query("id") int id);

}
