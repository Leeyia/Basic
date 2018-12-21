package com.racofix.basic.bluetooth.connection;

import android.bluetooth.BluetoothGatt;

/**
 * Interface for single operation on {@link BluetoothGatt}.
 */
public interface GattOperation {
    void execute(BluetoothGatt gatt);
}
