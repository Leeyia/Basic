package com.dintech.api.samples.apis;

import com.dintech.api.http.interceptors.HttpLogInterceptor;
import com.dintech.api.http.Network;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

public class NeT {

    private final static Network neT;

    static {
        neT = new Network.Builder()
                .baseURL("http://www.dintech.com/getApiService/v2/")
                .converter(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLogInterceptor())
                        .build())
                .build();
    }

    public static ApiService getApiService() {
        return neT.servs(ApiService.class);
    }
}
