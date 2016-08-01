package com.android.core.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:15
 * @GitHub: https://github.com/meikoz
 */
public class HttpClient {

    private static HttpClient mInstance;
    private Retrofit singleton;

    public static HttpClient getIns(String base_url) {
        if (mInstance == null) {
            synchronized (HttpClient.class) {
                if (mInstance == null) mInstance = new HttpClient(base_url);
            }
        }
        return mInstance;
    }

    public HttpClient(String BASE_URL) {

        singleton = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OKHttpHelper.create())
                .build();
    }

    public <T> T createService(Class<T> clz) {
        return singleton.create(clz);
    }
}
