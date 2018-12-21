package com.racofix.basic.bluetooth.scanning;

import com.racofix.basic.bluetooth.BlueRock;
import com.racofix.basic.bluetooth.conf.ScanConfigure;

public interface ScanOperation {

    void start();

    void stop();

    boolean isScanning();

    void destroy();

    void setScanConfig(ScanConfigure config);

    void addOnScanCallback(BlueRock.OnScanCallback callback);
}
