package com.dintech.api.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dintech.api.bleep.BluetoothKit;

public class MainActivity extends AppCompatActivity {

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

        BluetoothKit.get();
    }
}
