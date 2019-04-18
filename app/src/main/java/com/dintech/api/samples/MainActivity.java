package com.dintech.api.samples;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dintech.api.outside.bluetooth.BluetoothKit;
import com.dintech.api.outside.bluetooth.OperationCallback;
import com.dintech.api.http.TCallback;
import com.dintech.api.samples.apis.NeT;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        VLog.d("message", "ssss");

        NeT.getApiService().user().enqueue(new TCallback<Response>() {
            @Override
            public void done(Response body) {

            }

            @Override
            public void fail(String message) {

            }
        });


        BluetoothKit.getDefalut().connect("sssss", new OperationCallback() {
            @Override
            public void done(BluetoothDevice device) {

            }

            @Override
            public void fail(String message) {

            }
        });

        BluetoothKit.getDefalut().notiy("sssss", "sss");
    }
}
