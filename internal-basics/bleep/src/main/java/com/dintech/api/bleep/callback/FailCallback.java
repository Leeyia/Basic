package com.dintech.api.bleep.callback;

import android.bluetooth.BluetoothDevice;

public interface FailCallback {

    void onRequestFailed(BluetoothDevice device, String message);
}
