package com.dintech.api.bleep.callback;

import android.bluetooth.BluetoothDevice;

public interface DisConnectionCallback {
    void onDisConnected(BluetoothDevice device);
}
