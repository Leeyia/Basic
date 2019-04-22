package com.dintech.api.bleep.operation;

import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.Request;

public class SystemOperation implements Operation {

    @Override
    public void connect(BluetoothDevice device, Request request) {

    }

    @Override
    public void disconnect(BluetoothDevice device, Request request) {

    }

    @Override
    public void notification(BluetoothDevice device, String serviceUuid, String characterUuid, Request request) {

    }

    @Override
    public void write(BluetoothDevice device, String serviceUuid, String characterUuid, byte[] data, boolean split, Request request) {

    }
}
