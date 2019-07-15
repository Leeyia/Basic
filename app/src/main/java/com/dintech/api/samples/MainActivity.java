package com.dintech.api.samples;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import com.dintech.api.bleep.Bleep;
import com.dintech.api.bleep.callback.FailCallback;
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

        Bleep.getInstance()
                .connect("BA:03:29:36:9A:B5")
                .timeout(10000)
                .done(device -> {
                    BluetoothDevice device1 = device;
                })
                .fail(new FailCallback() {
                    @Override
                    public void onRequestFailed(BluetoothDevice device, BleException e) {
                        BluetoothDevice device1 = device;
                    }
                })
                .enqueue();

    }
}
