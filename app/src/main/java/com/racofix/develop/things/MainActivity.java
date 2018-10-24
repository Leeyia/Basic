package com.racofix.develop.things;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.racofix.develop.bluetooth.BleDevice;
import com.racofix.develop.bluetooth.BleScanCallback;
import com.racofix.develop.bluetooth.BluetoothKit;
import com.racofix.develop.bluetooth.ScanConfig;

import java.util.List;

public class MainActivity extends Activity implements BleScanCallback {

    private BluetoothKit kit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScanConfig config = new ScanConfig.Builder()
                .scanPeriodMills(1000)
                .scanBetweenMills(5000)
                .scanBLEFilters("0000fff")
                .openPeriod(true)
                .build();

        kit = BluetoothKit.getInstance(this);
        kit.setScanConfig(config);
        kit.setBleScanCallback(this);
        kit.startLeScan();

    }

    @Override
    public void onLeScan(BleDevice device) {
        Log.d("test", device.getDevice().getName() + " rssi: " + device.getRssi());
    }

    @Override
    public void onScanPeriodFinish(List<BleDevice> devices) {
        Log.i("test", "onScanPeriodFinish = " + devices.size() + "个");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        kit.stopLeScan();
    }
}
