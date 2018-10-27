package com.racofix.develop.bluetooth.callback;


import com.racofix.develop.bluetooth.model.BleDevice;

/**
 * Created by pw on 2018/9/13.
 */

public interface BleCallback {
    int FAIL_DISCONNECTED = 200;
    int FAIL_OTHER = 201;

    void failure(int failCode, String info, BleDevice device);
}
