package com.dintech.api.bleep.internal;

import android.bluetooth.BluetoothDevice;

import com.dintech.api.bleep.Request;
import com.dintech.api.bleep.Trigger;

public class SystemTrigger implements Trigger {

    @Override
    public void connect(BluetoothDevice device, Request request) {

    }

    @Override
    public void notification(BluetoothDevice device, Request request) {

    }

    @Override
    public void write(BluetoothDevice device, byte[] bytes, Request request) {

    }

    @Override
    public void disconnect(BluetoothDevice device, Request request) {

    }
}
