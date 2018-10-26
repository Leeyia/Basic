package com.racofix.develop.bluetooth.callback;

import com.racofix.develop.bluetooth.model.BleDevice;

import java.util.List;

public interface BleScanCallback {

    void onLeScan(BleDevice device);

    void onScanPeriodFinish(List<BleDevice> devices);
}
