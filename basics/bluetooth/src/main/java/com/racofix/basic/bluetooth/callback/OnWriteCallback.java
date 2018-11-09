package com.racofix.basic.bluetooth.callback;

import com.racofix.basic.bluetooth.model.BleDevice;

public interface OnWriteCallback extends BleCallback {

    void writed(byte[] bytes, BleDevice device);
}
