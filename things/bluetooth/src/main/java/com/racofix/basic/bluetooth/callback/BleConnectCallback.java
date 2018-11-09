package com.racofix.basic.bluetooth.callback;

import com.racofix.basic.bluetooth.model.BleDevice;

public interface BleConnectCallback {

    void onStart(boolean connectState, String info, BleDevice device);

    void onConnect(BleDevice device);

    void onTimeout(BleDevice device);

    void onDisconnect(BleDevice device);
}
