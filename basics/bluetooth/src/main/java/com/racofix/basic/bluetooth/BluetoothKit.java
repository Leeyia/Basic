package com.racofix.basic.bluetooth;

import android.app.Application;

import com.racofix.basic.bluetooth.conf.ContextWrf;
import com.racofix.basic.bluetooth.scanning.ScanningProvider;
import com.racofix.basic.bluetooth.scanning.ScanningProviderFactory;
import com.racofix.basic.bluetooth.utils.Preconditions;

public class BluetoothKit {

    private static BluetoothKit bluetoothKit;

    public static void initialize(Application application) {
        ContextWrf.initialize(application.getApplicationContext());
    }

    public static BluetoothKit getDefalut() {
        if (bluetoothKit == null) {
            synchronized (BluetoothKit.class) {
                if (bluetoothKit == null) bluetoothKit = new BluetoothKit();
            }
        }
        return bluetoothKit;
    }

    private BluetoothKit() {
        Preconditions.checkNotNull(ContextWrf.get(), "You need to initialize BluetoothKit first. " +
                "BluetoothKit.initialize(application)");
    }

    public ScanningProvider getScanningProvider() {
        return ScanningProviderFactory.create();
    }
}
