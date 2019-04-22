package com.dintech.api.bleep.operation;

import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.Request;

public interface Operation {

    void connect(BluetoothDevice device, Request request);

    void disconnect(BluetoothDevice device, Request request);

    void notification(BluetoothDevice device, String serviceUuid, String characterUuid, Request request);

    void write(BluetoothDevice device, String serviceUuid, String characterUuid, byte[] data, boolean split, Request request);
}
