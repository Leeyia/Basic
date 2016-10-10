package com.racofix.basic.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @User: 蜡笔小新
 * @date: 16-10-10
 * @GitHub: https://github.com/meikoz
 */

public class RestPool {
    public static ApiServiceI create() {
        return new Retrofit.Builder()
                .baseUrl("base_url")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(ApiServiceI.class);
    }
}
