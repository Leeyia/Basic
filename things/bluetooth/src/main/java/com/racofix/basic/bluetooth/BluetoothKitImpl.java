package com.racofix.basic.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;

import com.racofix.basic.bluetooth.callback.BleScanCallback;
import com.racofix.basic.bluetooth.model.BleDevice;
import com.racofix.basic.logger.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BluetoothKitImpl implements BluetoothKit {

    private Context context;
    private Handler handler;

    private boolean scanEnable;
    private boolean scanPeriodStarted;

    private List<BleDevice> scanDevices;
    private final Object mLock1 = new Object();

    private BleScanCallback scanCallback;
    private BluetoothConfig scanConfig;

    private BluetoothGattControll gattControll;
    private BluetoothAdapter2 bluetoothAdapter2;
    private static volatile BluetoothKit mKit;


    private Runnable scanPeriodStartRunnanle = new Runnable() {
        public void run() {
            BluetoothKitImpl.this.scanLeDevice(true);
        }
    };
    private Runnable scanPeriodStopRunnanle = new Runnable() {
        public void run() {
            BluetoothKitImpl.this.scanLeDevice(false);
            BluetoothKitImpl.this.onScanPeriodFinish();
        }
    };
    private BluetoothAdapter.LeScanCallback callback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, byte[] scanRecord) {
            if (BluetoothKitImpl.this.scanCallback != null) {
                filterDevice(device, rssi, scanRecord);
            }
        }
    };

    /**
     * 构造函数
     *
     * @param context
     */
    private BluetoothKitImpl(Context context) {
        this.context = context;
        this.handler = new Handler();
        this.scanDevices = new ArrayList<>();
        this.bluetoothAdapter2 = new BluetoothAdapter2Impl(context);
    }


    /**
     * BluetoothKit 实例
     *
     * @param context context
     * @return 实例
     */
    public static BluetoothKit getInstance(Context context) {
        if (BluetoothKitImpl.mKit == null) {
            synchronized (BluetoothKitImpl.class) {
                if (BluetoothKitImpl.mKit == null)
                    BluetoothKitImpl.mKit = new BluetoothKitImpl(context);
            }
        }
        return BluetoothKitImpl.mKit;
    }

    /**
     * start scan device
     */
    @Override
    public void startLeScan() {
        LogUtil.d("bluetooth scanner start");
        this.scanEnable = true;
        if (!this.scanPeriodStarted) {
            LogUtil.d("bluetooth scanning not start,remove cycle start,start scan");
            this.handler.removeCallbacks(this.scanPeriodStartRunnanle);
            this.scanLeDevice(true);
        } else {
            LogUtil.d("bluetooth scanning already started");
        }
    }

    /**
     * stop scan device
     */
    @Override
    public void stopLeScan() {
        LogUtil.d("bluetooth scanner stop");
        this.scanEnable = false;
        if (this.scanPeriodStarted) {
            LogUtil.d("bluetooth scanning start,remove cycle stop,stop scan");
            this.handler.removeCallbacks(this.scanPeriodStopRunnanle);
            this.scanLeDevice(false);
        } else {
            LogUtil.d("bluetooth scanning already stop,remove cycle start");
            this.handler.removeCallbacks(this.scanPeriodStartRunnanle);
        }
    }

    @Override
    public BluetoothGattControll getGattControll() {
        checkBleGattInterceptor();
        return this.gattControll;
    }

    @Override
    public void checkBleGattInterceptor() {
        if (gattControll == null) {
            synchronized (mLock1) {
                if (gattControll == null)
                    this.gattControll = new BluetoothGattControllImpl(context);
            }
        }
    }

    //资源释放
    @SuppressLint("MissingPermission")
    @Override
    public void onDestory() {
        this.stopLeScan();

        if (gattControll != null) {
            gattControll.onDestory();
            gattControll = null;
        }

        //fixme not make sure release
        if (bluetoothAdapter2 != null) {
            BluetoothAdapter adapter = bluetoothAdapter2.getBluetoothAdapter();
            if (adapter.isEnabled()) adapter.disable();
            bluetoothAdapter2 = null;
        }
    }

    @Override
    public BluetoothAdapter getBluetoothAdapter() {
        return this.bluetoothAdapter2.getBluetoothAdapter();
    }

    /**
     * 是否正在扫描
     *
     * @return 状态
     */
    @Override
    public boolean isScanning() {
        return this.scanPeriodStarted;
    }

    @Override
    public boolean isConnected(BleDevice device) {
        return device.connected;
    }

    /**
     * 是否开启过滤
     *
     * @return 状态
     */
    @Override
    public boolean isOpenFiltered() {
        return this.scanConfig.getScanFilters() != null && this.scanConfig.getScanFilters().length > 0;
    }

    @Override
    public void setBluetoothConfig(BluetoothConfig config) {
        this.scanConfig = config;
    }

    @Override
    public void setBluetoothScanCallback(BleScanCallback scanCallback) {
        this.scanCallback = scanCallback;
    }


    /**
     * 过滤设备
     *
     * @param device
     * @param rssi
     * @param scanRecord
     */
    private void filterDevice(BluetoothDevice device, int rssi, byte[] scanRecord) {
        //Bluetooth 扫描不去重并且不过滤
        if (!this.scanConfig.isRemoveDuplicated() && !isOpenFiltered()) {
            addDevice(device, rssi, scanRecord);
            return;
        }

        //Bluetooth 扫描不去重并且不过滤
        if (!this.scanConfig.isRemoveDuplicated() && isOpenFiltered()) {
            if (filterResulted(device)) {
                addDevice(device, rssi, scanRecord);
            }
            return;
        }

        //Bluetooth 扫描去重并考虑过滤不过滤
        if (this.scanConfig.isRemoveDuplicated()) {
            boolean deviceFound = false;
            for (BleDevice listDev : scanDevices) {
                if (listDev.getDevice().getAddress().equals(device.getAddress())) {
                    deviceFound = true;
                    break;
                }
            }
            if (!deviceFound) {
                if (isOpenFiltered()) {
                    if (filterResulted(device)) {
                        addDevice(device, rssi, scanRecord);
                    }
                } else {
                    addDevice(device, rssi, scanRecord);
                }
            }
        }
    }

    /**
     * 添加蓝牙到集合中
     *
     * @param device
     * @param rssi
     * @param scanRecord
     */
    private void addDevice(BluetoothDevice device, int rssi, byte[] scanRecord) {
        BleDevice bleDevice = new BleDevice(device, rssi, scanRecord);
        BluetoothKitImpl.this.scanCallback.onLeScan(bleDevice);
        BluetoothKitImpl.this.scanDevices.add(bleDevice);
    }


    /**
     * 蓝牙是否符合过滤标准[name address]
     * v2 添加 rssi
     *
     * @param device
     * @return 结果
     */
    private boolean filterResulted(BluetoothDevice device) {
        List<String> filters = Arrays.asList(this.scanConfig.getScanFilters());
        return (filters.contains(device.getName()) || filters.contains(device.getAddress()));
    }

    /**
     * 开启扫描
     */
    @SuppressLint("MissingPermission")
    private void scanLeDevice(boolean enable) {
        if (enable) {
            if (!this.scanPeriodStarted) {
                this.scanPeriodStarted = true;
                if (this.scanEnable) {
                    this.bluetoothAdapter2.getBluetoothAdapter().startLeScan(callback);
                    if (BluetoothKitImpl.this.scanConfig.periodOpened()) {
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
            this.bluetoothAdapter2.getBluetoothAdapter().stopLeScan(callback);
            this.scanPeriodStarted = false;
        }
    }

    /**
     * 周期扫描完成
     * <p>
     * - 清除缓存集合
     * - 延迟 scanBetweenMills 开启扫描
     * <p/>
     */
    private void onScanPeriodFinish() {
        LogUtil.d("Bluetooth scan cycle finish");
        this.scanCallback.onScanPeriodFinish(scanDevices);
        this.scanDevices.clear();
        LogUtil.d("Bluetooth Period List clear");
        if (this.scanEnable) {
            this.handler.postDelayed(this.scanPeriodStartRunnanle, this.scanConfig.getScanBetween());
        } else {
            LogUtil.d("scanner not enable - no more scan");
        }
    }
}
