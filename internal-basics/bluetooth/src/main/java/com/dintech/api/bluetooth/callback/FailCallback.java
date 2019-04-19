package com.dintech.api.bluetooth.callback;

import android.bluetooth.BluetoothDevice;

public interface FailCallback {

    void onRequestFailed(BluetoothDevice device, String message);
}
