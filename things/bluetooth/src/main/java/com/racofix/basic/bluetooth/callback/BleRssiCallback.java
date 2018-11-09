package com.racofix.basic.bluetooth.callback;

import com.racofix.basic.bluetooth.model.BleDevice;

public interface BleRssiCallback extends BleCallback {

    void onRssi(int rssi, BleDevice bleDevice);
}
