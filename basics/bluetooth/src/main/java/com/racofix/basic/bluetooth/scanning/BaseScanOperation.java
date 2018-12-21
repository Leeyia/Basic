package com.racofix.basic.bluetooth.scanning;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import android.os.Handler;

import com.racofix.basic.bluetooth.BlueRock;
import com.racofix.basic.bluetooth.conf.ContextWrf;
import com.racofix.basic.bluetooth.conf.ScanConfigure;
import com.racofix.basic.bluetooth.utils.L;
import com.racofix.basic.bluetooth.utils.Preconditions;

/**
 * #小新.
 * #2018-12-21.
 */
public abstract class BaseScanOperation implements ScanOperation {

    private boolean scannerEnable;
    private boolean scanCycleStarted;
    private Handler handler = new Handler();
    private ScanConfigure config;
    private BluetoothAdapter bluetoothAdapter;
    private BlueRock.OnScanCallback onScanCallback;

    private Runnable scanCycleStartRunnanle = new Runnable() {
        public void run() {
            BaseScanOperation.this.scanLeDevice(true);
        }
    };
    private Runnable scanCycleStopRunnanle = new Runnable() {
        public void run() {
            BaseScanOperation.this.scanLeDevice(false);
            BaseScanOperation.this.scanCycleComplete();
        }
    };

    public static ScanOperation get() {
        if (Build.VERSION.SDK_INT < 18) {
            L.d("Not supported prior to API 18.");
            return null;
        } else {
            boolean useAndroidLeScanner;
            if (Build.VERSION.SDK_INT < 21) {
                L.d("This is not Android 5.0.  We are using old scanning APIs");
                useAndroidLeScanner = false;
            } else {
                L.d("This Android 5.0.  We are using new scanning APIs");
                useAndroidLeScanner = true;
            }

            return useAndroidLeScanner ? new ScanOperationForLollipop() : new ScanOperationForJellyBean();
        }
    }

    public void start() {
        L.d("bluetooth scanner start");
        this.scannerEnable = true;
        if (!this.scanCycleStarted) {
            L.d("bluetooth scanning not start,remove cycle start,start scan");
            this.handler.removeCallbacks(this.scanCycleStartRunnanle);
            this.scanLeDevice(true);
        } else {
            L.d("bluetooth scanning already started");
        }

    }

    public void stop() {
        L.d("bluetooth scanner stop");
        this.scannerEnable = false;
        if (this.scanCycleStarted) {
            L.d("bluetooth scanning start,remove cycle stop,stop scan");
            this.handler.removeCallbacks(this.scanCycleStopRunnanle);
            this.scanLeDevice(false);
        } else {
            L.d("bluetooth scanning already stop,remove cycle start");
            this.handler.removeCallbacks(this.scanCycleStartRunnanle);
        }

    }

    @Override
    public boolean isScanning() {
        return this.scanCycleStarted;
    }

    @Override
    public void setScanConfig(ScanConfigure config) {
        this.config = config;
    }

    public BlueRock.OnScanCallback getScanCallback() {
        return this.onScanCallback;
    }

    @Override
    public void addOnScanCallback(BlueRock.OnScanCallback callback) {
        this.onScanCallback = Preconditions.checkNotNull(callback, "OnScanCallback is null");
    }

    @SuppressLint("MissingPermission")
    protected BluetoothAdapter getBluetoothAdapter() {
        if (this.bluetoothAdapter == null) {
            android.bluetooth.BluetoothManager bluetoothManager = (android.bluetooth.BluetoothManager) ContextWrf.get().getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
            this.bluetoothAdapter = bluetoothManager.getAdapter();
            if (this.bluetoothAdapter == null) {
                L.d("Bluetooth Failed to construct a BluetoothAdapter");
            }

            if (!bluetoothAdapter.isEnabled()) bluetoothAdapter.enable();
        }

        return this.bluetoothAdapter;
    }

    private void scanLeDevice(boolean enable) {
        if (enable) {
            L.d("Bluetooth scanLeDevice true: starting a new scan cycle");
            if (!this.scanCycleStarted) {
                this.scanCycleStarted = true;
                if (this.scannerEnable) {
                    this.startScan();
                    this.handler.postDelayed(this.scanCycleStopRunnanle, this.config.getPeriodMills());
                } else {
                    L.d("Bluetooth scanner not enable, unnecessary to scan");
                }
            } else {
                L.d("Bluetooth already scanning");
            }
        } else {
            this.stopScan();
            this.scanCycleStarted = false;
        }
    }

    private void scanCycleComplete() {
        L.d("Bluetooth scan cycle finish");
        this.onScanCallback.onScanCycleCompleted();
        if (this.scannerEnable) {
            this.handler.postDelayed(this.scanCycleStartRunnanle, this.config.getWaitMills());
        } else {
            L.d("scanner not enable - no more scan");
        }
    }

    @Override
    public void destroy() {
        this.stop();
    }

    protected abstract void startScan();

    protected abstract void stopScan();
}
