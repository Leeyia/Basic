package com.racofix.basic.bluetooth.scanning;

import com.racofix.basic.bluetooth.BlueRock;
import com.racofix.basic.bluetooth.conf.ScanConfigure;
import com.racofix.basic.bluetooth.utils.Preconditions;

public class ScanOperationProxy implements ScanOperation {

    private static ScanOperation instance;
    private ScanOperation operation;

    private ScanOperationProxy(ScanOperation operation) {
        this.operation = Preconditions.checkNotNull(operation, "scanner is null");
    }

    public static ScanOperation getInstance(ScanOperation operation) {
        if (instance == null)
            synchronized (ScanOperationProxy.class) {
                if (instance == null) instance = new ScanOperationProxy(operation);
            }
        return instance;
    }

    @Override
    public void start() {
        this.operation.start();
    }

    @Override
    public void stop() {
        this.operation.stop();
    }

    @Override
    public boolean isScanning() {
        return this.operation.isScanning();
    }

    @Override
    public void destroy() {
        this.operation.destroy();
    }

    @Override
    public void setScanConfig(ScanConfigure config) {
        this.operation.setScanConfig(config);
    }

    @Override
    public void addOnScanCallback(BlueRock.OnScanCallback callback) {
        this.operation.addOnScanCallback(callback);
    }
}
