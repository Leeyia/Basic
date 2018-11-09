package com.racofix.basic.bluetooth.callback;

import com.racofix.basic.bluetooth.model.BleDevice;

public interface BleNotifyCallback extends BleCallback {
    void onCharacteristicChanged(byte[] data, BleDevice device);

    void onNotifySuccess(String notifySuccessUuid, BleDevice device);
}
