package com.racofix.develop.bluetooth.callback;

import com.racofix.develop.bluetooth.model.BleDevice;

public interface BleReadCallback extends BleCallback {
    void onRead(byte[] data, BleDevice device);
}
