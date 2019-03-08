package com.dintech.android.variants.api;

import com.dintech.architecture.http.CallbackEvent;

public class NetWorkTest {

    public static void main(String[] args) {
        CallbackEvent<Girls> callbackEvent = new CallbackEvent<Girls>() {
            @Override
            public void done(Girls body) {
            }

            @Override
            public void fail(String message) {
            }
        };
        Api.getDefalt().get("http://gank.io/api/data/福利/10/1", callbackEvent);
    }
}
