package com.dintech.android.outside.bluetooth;

import android.bluetooth.BluetoothDevice;

public interface OperationCallback {

    void done(BluetoothDevice device);

    void fail(String message);
}
