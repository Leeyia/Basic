package com.dintech.api.bleep.callback;

import android.bluetooth.BluetoothDevice;

public interface ReceiveCallback {

    void onRequestReceive(BluetoothDevice device, byte[] bytes);
}
