package com.racofix.develop.bluetooth.callback;


import com.racofix.develop.bluetooth.model.BleDevice;

public interface BleMtuCallback extends BleCallback {
    void onMtuChanged(int mtu, BleDevice device);
}
