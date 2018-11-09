package com.racofix.basic.bluetooth.callback;


import com.racofix.basic.bluetooth.model.BleDevice;

public interface BleMtuCallback extends BleCallback {
    void onMtuChanged(int mtu, BleDevice device);
}
