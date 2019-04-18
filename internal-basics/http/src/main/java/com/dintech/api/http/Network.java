package com.dintech.api.http;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class Network {

    private final Retrofit retrofit;
    private final Map<Class, Object> servsMap = new HashMap<>();

    private Network(Builder builder) {
        retrofit = new Retrofit.Builder()
                .baseUrl(builder.baseUrl)
                .addConverterFactory(builder.factory)
                .addCallAdapterFactory(builder.callFactory)
                .client(builder.client)
                .build();
    }

    public <T> T servs(Class<T> clss) {
        Object serv = servsMap.get(clss);
        if (serv == null)
            synchronized (Network.class) {
                serv = servsMap.get(clss);
                if (serv == null) {
                    serv = retrofit.create(clss);
                    servsMap.put(clss, serv);
                }
            }
        return (T) serv;
    }

    /**
     * 构造RETROFIT
     */
    public static class Builder {
        private String baseUrl;
        private OkHttpClient client;
        private Converter.Factory factory;
        private CallAdapter.Factory callFactory;

        public Builder baseURL(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder client(OkHttpClient client) {
            this.client = client;
            return this;
        }

        public Builder converter(Converter.Factory factory) {
            this.factory = factory;
            return this;
        }

        public Builder call(CallAdapter.Factory factory) {
            this.callFactory = factory;
            return this;
        }

        public Network build() {
            return new Network(this);
        }
    }
}
