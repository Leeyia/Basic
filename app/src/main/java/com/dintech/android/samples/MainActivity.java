package com.dintech.android.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dintech.android.variants.Api;
import com.dintech.android.variants.Girls;
import com.dintech.architecture.http.CallbackEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallbackEvent<Girls> callbackEvent = new CallbackEvent<Girls>() {
            @Override
            public void done(Girls body) {
            }

            @Override
            public void fail(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                System.out.print("fail" + message);
            }
        };
        Api.getDefalt().get("http://gank.io/api/data/福利/10/1", callbackEvent);
    }
}
