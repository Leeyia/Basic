package com.racofix.develop.things;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.racofix.develop.bluetooth.BluetoothKit;
import com.racofix.develop.bluetooth.ScanConfig;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScanConfig config = new ScanConfig.Builder()
                .scanPeriod(1000)
                .scanBetween(1000)
                .startsWithFilter("0000fff")
                .build();

        BluetoothKit
                .getInstance(this)
                .setScanConfig(config);
    }
}
