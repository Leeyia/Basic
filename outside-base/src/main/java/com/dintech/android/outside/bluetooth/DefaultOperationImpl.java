package com.dintech.android.outside.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

public class DefaultOperationImpl implements Operation {

    @Override
    public void connect(String mac, OperationCallback callback) {
        Log.d("BluetoothKit", "connect " + mac);
    }

    @Override
    public void connect(BluetoothDevice device, OperationCallback callback) {
    }

    @Override
    public void notiy(String serviceUuid, String writeUuid) {
        Log.d("BluetoothKit", "notiy " + serviceUuid);
    }
}
