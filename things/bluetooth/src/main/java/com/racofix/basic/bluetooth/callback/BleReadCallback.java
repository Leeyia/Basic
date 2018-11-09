package com.racofix.basic.bluetooth.callback;

import com.racofix.basic.bluetooth.model.BleDevice;

public interface BleReadCallback extends BleCallback {
    void onRead(byte[] data, BleDevice device);
}
