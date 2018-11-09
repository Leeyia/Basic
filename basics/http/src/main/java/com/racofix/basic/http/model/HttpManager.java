package com.racofix.basic.http.model;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    public static Retrofit newBuilder() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RealCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://v.juhe.cn/")
                .client(new OkHttpClient())
                .build();
    }
}
