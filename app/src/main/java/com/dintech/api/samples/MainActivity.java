package com.dintech.api.samples;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dintech.android.samples.R;
import com.dintech.api.log.VLog;
import com.dintech.android.outside.api.Api;
import com.dintech.android.outside.api.Girls;
import com.dintech.android.outside.bluetooth.BluetoothKit;
import com.dintech.android.outside.bluetooth.OperationCallback;
import com.dintech.android.outside.gson.GsonConverterFactory;
import com.dintech.android.http.CallbackEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VLog.d("message","ssss");

        CallbackEvent<Girls> callbackEvent = new CallbackEvent<Girls>() {
            @Override
            public void done(Girls body) {
                String object2Json = GsonConverterFactory.getDefalut().object2Json(body);
            }

            @Override
            public void fail(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                System.out.print("fail" + message);
            }
        };
        Api.getDefalt().get("http://gank.io/api/data/福利/10/1", callbackEvent);


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
