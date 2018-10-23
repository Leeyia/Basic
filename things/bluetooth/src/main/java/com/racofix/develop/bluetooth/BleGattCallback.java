package com.racofix.develop.bluetooth;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;

public interface BleGattCallback {

    void onConnectionStateChange(BluetoothGatt gatt, int status, int newState);

    void onServicesDiscovered(BluetoothGatt gatt, int status);

    void onCharacteristicRead(BluetoothGatt gatt,
                              BluetoothGattCharacteristic characteristic,
                              int status);

    void onCharacteristicChanged(BluetoothGatt gatt,
                                 BluetoothGattCharacteristic characteristic);
}
