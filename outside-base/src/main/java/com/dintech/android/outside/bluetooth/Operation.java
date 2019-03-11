package com.dintech.android.outside.bluetooth;

import android.bluetooth.BluetoothDevice;

import java.util.UUID;

public interface Operation {

    void connect(String mac, OperationCallback callback);

    void connect(BluetoothDevice device, OperationCallback callback);

    void notiy(String serviceUuid, String writeUuid);
}
