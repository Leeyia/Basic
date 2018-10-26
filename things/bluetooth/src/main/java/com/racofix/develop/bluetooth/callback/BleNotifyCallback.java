package com.racofix.develop.bluetooth.callback;

import com.racofix.develop.bluetooth.model.BleDevice;

public interface BleNotifyCallback extends BleCallback {
    void onCharacteristicChanged(byte[] data, BleDevice device);

    void onNotifySuccess(String notifySuccessUuid, BleDevice device);
}
