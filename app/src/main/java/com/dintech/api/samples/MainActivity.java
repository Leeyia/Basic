package com.dintech.api.samples;

import android.app.Activity;
import android.os.Bundle;

import com.dintech.api.bleep.BluetoothKit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        VLog.d("message", "ssss");

//        NeT.getApiService().user().enqueue(new TCallback<Response>() {
//            @Override
//            public void done(Response body) {
//
//            }
//
//            @Override
//            public void fail(String message) {
//
//            }
//        });

        BluetoothKit
                .getInstance()
                .connect("")
                .timeout(10000)
                .done(device -> {
                })
                .fail((device, ex) -> {
                })
                .enqueue();

    }
}
