package com.racofix.develop.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.content.Context;
import android.os.Handler;

import com.racofix.develop.logger.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BluetoothKit {

    private Context context;
    private Handler handler;
    private boolean scannerEnable;
    private boolean scanPeriodStarted;

    private ScanConfig scanConfig;
    private BleScanCallback scanCallback;
    private BleGattCallback mGattCallback;

    private String mBluetoothDeviceAddress;
    private BluetoothGatt mBluetoothGatt;
    private int mConnectionState = STATE_DISCONNECTED;
    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_CONNECTED = 2;

    private List<BleDevice> devices;

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
                addDevice(device, rssi, scanRecord);
            }
        }
    };

    private void addDevice(BluetoothDevice device, int rssi, byte[] scanRecord) {
        //Bluetooth 扫描不去重并且不过滤
        if (!this.scanConfig.isRemoveDuplicated() && !isDeviceFiltered()) {
            addDevice2List(device, rssi, scanRecord);
            return;
        }

        //Bluetooth 扫描不去重并且不过滤
        if (!this.scanConfig.isRemoveDuplicated() && isDeviceFiltered()) {
            if (filterResulted(device)) {
                addDevice2List(device, rssi, scanRecord);
            }
            return;
        }

        //Bluetooth 扫描去重并考虑过滤不过滤
        if (this.scanConfig.isRemoveDuplicated()) {
            boolean deviceFound = false;
            for (BleDevice listDev : devices) {
                if (listDev.getDevice().getAddress().equals(device.getAddress())) {
                    deviceFound = true;
                    break;
                }
            }
            if (!deviceFound) {
                if (isDeviceFiltered()) {
                    if (filterResulted(device)) {
                        addDevice2List(device, rssi, scanRecord);
                    }
                } else {
                    addDevice2List(device, rssi, scanRecord);
                }
            }
        }
/*
        if (!this.scanConfig.isRemoveDuplicated()) {
            if (isDeviceFiltered()) {
                if (filterResulted(device)) {
                    addDevice2List(device, rssi, scanRecord);
                }
            } else {
                addDevice2List(device, rssi, scanRecord);
            }
        } else {
            boolean deviceFound = false;
            for (BleDevice listDev : devices) {
                if (listDev.getDevice().getAddress().equals(device.getAddress())) {
                    deviceFound = true;
                    break;
                }
            }
            if (!deviceFound) {
                if (isDeviceFiltered()) {
                    if (filterResulted(device)) {
                        addDevice2List(device, rssi, scanRecord);
                    }
                } else {
                    addDevice2List(device, rssi, scanRecord);
                }
            }
        }*/
    }

    /**
     * 添加蓝牙到集合中
     *
     * @param device
     * @param rssi
     * @param scanRecord
     */
    private void addDevice2List(BluetoothDevice device, int rssi, byte[] scanRecord) {
        BleDevice bleDevice = new BleDevice(device, rssi, scanRecord);
        BluetoothKit.this.scanCallback.onLeScan(bleDevice);
        BluetoothKit.this.devices.add(bleDevice);
    }

    /**
     * 是否开启过滤
     *
     * @return
     */
    private boolean isDeviceFiltered() {
        return this.scanConfig.getScanFilters() != null && this.scanConfig.getScanFilters().length > 0;

    }

    /**
     * 蓝牙是否符合过滤标准[name address]
     * v2 添加 uuid
     *
     * @param device
     * @return 结果
     */
    private boolean filterResulted(BluetoothDevice device) {
        List<String> filters = Arrays.asList(this.scanConfig.getScanFilters());
        return (filters.contains(device.getName()) || filters.contains(device.getAddress()));
    }

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

        this.context = context;
        this.handler = new Handler();
        this.devices = new ArrayList<>();
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
     * Connects to the GATT server hosted on the Bluetooth LE device.
     *
     * @param address The device address of the destination device.
     * @return Return true if the connection is initiated successfully. The connection result
     * is reported asynchronously through the
     * {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     * callback.
     */
    public boolean connect(final String address) {
        if (this.mBluetoothLogic.getBluetoothAdapter() == null || address == null) {
            LogUtil.w("BluetoothAdapter not initialized or unspecified address.");
            return false;
        }

        // Previously connected device.  Try to reconnect.
        if (mBluetoothDeviceAddress != null && address.equals(mBluetoothDeviceAddress)
                && mBluetoothGatt != null) {
            LogUtil.d("Trying to use an existing mBluetoothGatt for connection.");
            if (mBluetoothGatt.connect()) {
                mConnectionState = STATE_CONNECTING;
                return true;
            } else {
                return false;
            }
        }

        final BluetoothDevice device = this.mBluetoothLogic.getBluetoothAdapter().getRemoteDevice(address);
        if (device == null) {
            LogUtil.w("Device not found.  Unable to connect.");
            return false;
        }
        // We want to directly connect to the device, so we are setting the autoConnect
        // parameter to false.
        mBluetoothGatt = device.connectGatt(context, false, new BluetoothGattCallback() {
        });
        LogUtil.d("Trying to create a new connection.");
        mBluetoothDeviceAddress = address;
        mConnectionState = STATE_CONNECTING;

        return true;
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
                    if (BluetoothKit.this.scanConfig.periodOpened()) {
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
