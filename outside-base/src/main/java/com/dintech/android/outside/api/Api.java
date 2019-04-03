package com.dintech.android.outside.api;

import com.dintech.android.http.Network;
import com.dintech.android.http.NetworkApi;
import com.dintech.android.http.NetworkProxy;

import java.lang.reflect.Proxy;

public class Api {

    /*动态代理*/
    public static Network getDefalt() {
        Network network = new OkHttpNetworkImpl();
        return (Network) Proxy.newProxyInstance(network.getClass().getClassLoader(), network.getClass().getInterfaces(), new NetworkProxy(network));
    }

    /*代理模式*/
    public static Network getOKHttp() {
        Network network = new OkHttpNetworkImpl();
        return NetworkApi.getInstance(network);
    }
}
