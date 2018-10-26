package com.racofix.develop.bluetooth.callback;

import com.racofix.develop.bluetooth.model.BleDevice;

public interface BleWriteCallback extends BleCallback {
    void onWrite(byte[] data, BleDevice device);
}
