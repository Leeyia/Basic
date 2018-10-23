package com.racofix.develop.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;

public class BluetoothKit {

    public static BluetoothKit getInstance(Context context) {
        return new BluetoothKit(context);
    }

    private BluetoothKit(Context context) {

    }

    public void setScanConfig(ScanConfig config) {
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, final int rssi, byte[] scanRecord) {
                }
            };
}
