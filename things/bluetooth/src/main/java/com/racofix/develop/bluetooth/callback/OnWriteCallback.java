package com.racofix.develop.bluetooth.callback;

import com.racofix.develop.bluetooth.model.BleDevice;

public interface OnWriteCallback extends BleCallback {

    void writed(byte[] bytes, BleDevice device);
}
