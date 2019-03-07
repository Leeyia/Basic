package com.dintech.architecture.http;

import java.util.Map;

public class NetworkApi implements Network {

    private final Network mNetwork;
    private static NetworkApi mInstance;

    private NetworkApi(Network network) {
        this.mNetwork = network;
    }

    public static NetworkApi getInstance(Network network) {
        if (mInstance == null) synchronized (NetworkApi.class) {
            if (mInstance == null) mInstance = new NetworkApi(network);
        }
        return mInstance;
    }

    @Override
    public <T> void get(String url, CallbackEvent<T> callbackEvent) {
        this.mNetwork.get(url, callbackEvent);
    }

    @Override
    public <T> void post(String url, Map<String, Object> paramMaps, CallbackEvent<T> callbackEvent) {
        this.mNetwork.post(url, paramMaps, callbackEvent);
    }
}
