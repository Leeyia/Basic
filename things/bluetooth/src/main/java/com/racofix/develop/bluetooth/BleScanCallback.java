package com.racofix.develop.bluetooth;

import java.util.List;

public interface BleScanCallback {

    void onLeScan(BleDevice device);

    void onScanPeriodFinish(List<BleDevice> devices);
}
