package com.dintech.api.bleep.internal;

import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.Request;

public interface Operation {

    void connect(BluetoothDevice device, Request request);

    void notification(BluetoothDevice device, Request request);

    void write(BluetoothDevice device, byte[] bytes, Request request);

    void disconnect(BluetoothDevice device, Request request);
}
