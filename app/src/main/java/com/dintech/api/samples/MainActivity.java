package com.dintech.api.samples;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import com.dintech.api.bleep.Blueteeth;
import com.dintech.api.bleep.exception.BleException;

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

        Blueteeth
                .getInstance()
                .connect("BA:03:54:52:C6:D5", 1)
                .timeout(10000)
                .done(device -> {
                    BluetoothDevice device1 = device;
                })
                .fail((device, ex) -> {
                    BluetoothDevice device1 = device;
                    BleException ex1 = ex;
                })
                .enqueue();

    }
}
