package com.dintech.api.bleep;

import android.bluetooth.BluetoothDevice;

public interface Trigger {

    void connect(BluetoothDevice device, Request request);

    void notification(BluetoothDevice device, Request request);

    void write(BluetoothDevice device, byte[] bytes, Request request);

    void disconnect(BluetoothDevice device, Request request);
}
