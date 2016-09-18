package com.android.core.api;

import android.text.TextUtils;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: 蜡笔小新
 * @date: 2016-09-14 14:15
 * @GitHub: https://github.com/meikoz
 */
public class RestApi {

    private static RestApi mInstance;
    private Retrofit singleton;
    private Object obj = new Object();

    public static RestApi getIns() {
        if (mInstance == null) {
            synchronized (RestApi.class) {
                if (mInstance == null) mInstance = new RestApi();
            }
        }
        return mInstance;
    }

    // create retrofit singleton
    private Retrofit createApiClient(String baseUrl, boolean isDebug) {
        if (singleton == null) {
            synchronized (obj) {
                if (singleton == null) {
                    singleton = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(createOkHttpClient(isDebug))
                            .build();
                }
            }
        }
        return singleton;
    }

    // create api service singleton
    public <T> T createService(String baseUrl, boolean isDebug, Class<T> clz) {
        String service_url = "";
        try {
            Field field1 = clz.getField("BASE_URL");
            service_url = (String) field1.get(clz);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return createApiClient(
                TextUtils.isEmpty(service_url) ? baseUrl : service_url, isDebug).create(clz);
    }

    // create api service baseUrl singleton
    public <T> T createService(boolean isDebug, Class<T> clz) {
        String service_url = "";
        try {
            Field field1 = clz.getField("BASE_URL");
            service_url = (String) field1.get(clz);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return createApiClient(service_url, isDebug).create(clz);
    }


    // create okHttpClient singleton
    public OkHttpClient createOkHttpClient(boolean debug) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .addInterceptor(
                        new HttpLoggingInterceptor().setLevel(
                                debug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();
    }
}
