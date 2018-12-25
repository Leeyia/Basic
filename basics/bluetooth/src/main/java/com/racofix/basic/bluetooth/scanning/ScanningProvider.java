package com.racofix.basic.bluetooth.scanning;

import com.racofix.basic.bluetooth.BlueRock;
import com.racofix.basic.bluetooth.conf.ScanConfigure;

public interface ScanningProvider {

    /** SCANNING */
    void start();

    void stop();

    /** GENERAL */
    boolean isActive();

    void setScanConfig(ScanConfigure config);

    void addOnScanCallback(BlueRock.OnScanCallback callback);

    /** MANAGEMENT */
/*    void wakeup();*/

    void destroy();
/*
    void pause();

    void resume();*/
}
