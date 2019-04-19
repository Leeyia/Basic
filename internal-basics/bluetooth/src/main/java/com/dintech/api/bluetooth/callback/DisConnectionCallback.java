package com.dintech.api.bluetooth.callback;

import android.bluetooth.BluetoothDevice;

public interface DisConnectionCallback {
    void onDisConnected(BluetoothDevice device);
}
