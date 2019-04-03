package com.dintech.android.http;

import java.util.Map;

public interface Network {

    <T> void get(String url, CallbackEvent<T> callbackEvent);

    <T> void post(String url, Map<String, Object> paramMaps, CallbackEvent<T> callbackEvent);
}
