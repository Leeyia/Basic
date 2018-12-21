package com.racofix.basic.bluetooth;

import android.app.Application;

import com.racofix.basic.bluetooth.conf.ContextWrf;
import com.racofix.basic.bluetooth.scanning.BaseScanOperation;
import com.racofix.basic.bluetooth.scanning.ScanOperation;

public class BluetoothKit {

    private static BluetoothKit bluetoothKit;

    public static BluetoothKit getDefalut() {
        if (bluetoothKit == null) {
            synchronized (BluetoothKit.class) {
                if (bluetoothKit == null) bluetoothKit = new BluetoothKit();
            }
        }
        return bluetoothKit;
    }

    private BluetoothKit() {
    }

    public void initialize(Application application) {
        ContextWrf.initialize(application.getApplicationContext());
    }

    public ScanOperation getScanProxy() {
        return BaseScanOperation.get();
    }
}
