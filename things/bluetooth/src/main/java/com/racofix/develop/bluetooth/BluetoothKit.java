package com.racofix.develop.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;

import com.racofix.develop.logger.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class BluetoothKit {

    private Handler handler;
    private boolean scannerEnable;
    private boolean scanPeriodStarted;

    private ScanConfig scanConfig;
    private BleScanCallback scanCallback;
    private BleGattCallback mGattCallback;
    private List<BleDevice> devices = new ArrayList<>();

    private final BluetoothLogic mBluetoothLogic;
    private static volatile BluetoothKit mKit;
    private Runnable scanPeriodStartRunnanle = new Runnable() {
        public void run() {
            BluetoothKit.this.scanLeDevice(true);
        }
    };
    private Runnable scanPeriodStopRunnanle = new Runnable() {
        public void run() {
            BluetoothKit.this.scanLeDevice(false);
            BluetoothKit.this.onScanPeriodFinish();
        }
    };
    private BluetoothAdapter.LeScanCallback callback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, byte[] scanRecord) {
            if (BluetoothKit.this.scanCallback != null) {
                BleDevice bleDevice = new BleDevice(device, rssi, scanRecord);
                BluetoothKit.this.scanCallback.onLeScan(bleDevice);
                BluetoothKit.this.devices.add(bleDevice);
            }
        }
    };

    /**
     * BluetoothKit 实例
     *
     * @param context
     * @return
     */
    public static BluetoothKit getInstance(Context context) {
        if (BluetoothKit.mKit == null) {
            synchronized (BluetoothKit.class) {
                if (BluetoothKit.mKit == null)
                    BluetoothKit.mKit = new BluetoothKit(context);
            }
        }
        return BluetoothKit.mKit;
    }

    /**
     * 构造函数
     *
     * @param context
     */
    private BluetoothKit(Context context) {
        this.handler = new Handler();
        this.mBluetoothLogic = new BluetoothLogicImpl(context);
    }

    /**
     * 开启扫描
     */
    public void startLeScan() {
        LogUtil.d("bluetooth scanner start");
        this.scannerEnable = true;
        if (!this.scanPeriodStarted) {
            LogUtil.d("bluetooth scanning not start,remove cycle start,start scan");
            this.handler.removeCallbacks(this.scanPeriodStartRunnanle);
            this.scanLeDevice(true);
        } else {
            LogUtil.d("bluetooth scanning already started");
        }
    }

    /**
     * 停止扫描
     */
    public void stopLeScan() {
        LogUtil.d("bluetooth scanner stop");
        this.scannerEnable = false;
        if (this.scanPeriodStarted) {
            LogUtil.d("bluetooth scanning start,remove cycle stop,stop scan");
            this.handler.removeCallbacks(this.scanPeriodStopRunnanle);
            this.scanLeDevice(false);
        } else {
            LogUtil.d("bluetooth scanning already stop,remove cycle start");
            this.handler.removeCallbacks(this.scanPeriodStartRunnanle);
        }
    }

    /**
     * 开启扫描
     */
    @SuppressLint("MissingPermission")
    private void scanLeDevice(boolean enable) {
        if (enable) {
            if (!this.scanPeriodStarted) {
                this.scanPeriodStarted = true;
                if (this.scannerEnable) {
                    this.mBluetoothLogic.getBluetoothAdapter().startLeScan(callback);
                    if (BluetoothKit.this.scanConfig.isOpenPeriod()) {
                        LogUtil.d("Bluetooth open period scanner");
                        this.handler.postDelayed(this.scanPeriodStopRunnanle, this.scanConfig.getScanPeriod());
                    } else {
                        LogUtil.d("Bluetooth not open period scanner");
                    }
                } else {
                    LogUtil.d("Bluetooth scanner not enable, unnecessary to scan");
                }
            } else {
                LogUtil.d("Bluetooth already scanning");
            }
        } else {
            this.mBluetoothLogic.getBluetoothAdapter().stopLeScan(callback);
            this.scanPeriodStarted = false;
        }
    }

    /**
     * 周期扫描完成
     */
    private void onScanPeriodFinish() {
        LogUtil.d("Bluetooth scan cycle finish");
        this.scanCallback.onScanPeriodFinish(devices);
        this.devices.clear();
        LogUtil.d("Bluetooth Period List clear");
        if (this.scannerEnable) {
            this.handler.postDelayed(this.scanPeriodStartRunnanle, this.scanConfig.getScanBetween());
        } else {
            LogUtil.d("scanner not enable - no more scan");
        }
    }

    /**
     * 这事扫描配置
     *
     * @param config
     */
    public void setScanConfig(ScanConfig config) {
        this.scanConfig = config;
    }

    /**
     * 扫描回调
     *
     * @param scanCallback
     */
    public void setBleScanCallback(BleScanCallback scanCallback) {
        this.scanCallback = scanCallback;
    }

    /**
     * 连接回调
     *
     * @param gattCallback
     */
    public void setBleGattCallback(BleGattCallback gattCallback) {
        this.mGattCallback = gattCallback;
    }

    public boolean getScanPeriodStarted() {
        return this.scanPeriodStarted;
    }
}
