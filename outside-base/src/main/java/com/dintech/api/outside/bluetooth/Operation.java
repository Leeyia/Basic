package com.dintech.api.outside.bluetooth;

import android.bluetooth.BluetoothDevice;

public interface Operation {

    void connect(String mac, OperationCallback callback);

    void connect(BluetoothDevice device, OperationCallback callback);

    void notiy(String serviceUuid, String writeUuid);
}
