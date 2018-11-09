package com.racofix.basic.bluetooth.callback;

import com.racofix.basic.bluetooth.model.BleDevice;

public interface BleWriteByBatchCallback extends BleCallback {
    void writeByBatchSuccess(byte[] data, BleDevice device);
}
