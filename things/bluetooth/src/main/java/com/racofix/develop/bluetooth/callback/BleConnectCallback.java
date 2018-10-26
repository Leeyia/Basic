package com.racofix.develop.bluetooth.callback;

import com.racofix.develop.bluetooth.model.BleDevice;

public interface BleConnectCallback {

    void onStart(boolean connectState, String info, BleDevice device);

    void onConnect(BleDevice device);

    void onTimeout(BleDevice device);

    void onDisconnect(BleDevice device);
}
