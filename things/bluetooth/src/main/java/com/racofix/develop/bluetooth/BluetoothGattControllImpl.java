package com.racofix.develop.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.racofix.develop.bluetooth.callback.BleCallback;
import com.racofix.develop.bluetooth.callback.BleConnectCallback;
import com.racofix.develop.bluetooth.callback.BleMtuCallback;
import com.racofix.develop.bluetooth.callback.BleNotifyCallback;
import com.racofix.develop.bluetooth.callback.BleReadCallback;
import com.racofix.develop.bluetooth.callback.BleRssiCallback;
import com.racofix.develop.bluetooth.callback.BleWriteByBatchCallback;
import com.racofix.develop.bluetooth.callback.OnWriteCallback;
import com.racofix.develop.bluetooth.model.BleDevice;
import com.racofix.develop.bluetooth.model.CharacteristicEntity;
import com.racofix.develop.bluetooth.model.ServiceEntity;
import com.racofix.develop.logger.LogUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by pw on 2018/9/13.
 */
public class BluetoothGattControllImpl implements BluetoothGattControll {

    private static final String CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";

    private Context mContext;
    private int mConnectTimeout = 10000;//defalut 10s
    private Handler mHandler;
    private Map<BleDevice, BleConnectCallback> mConnectCallbackMap;
    private Map<String, BleMtuCallback> mMtuCallbackMap;
    private Map<String, BleRssiCallback> mRssiCallbackMap;
    private Map<String, BluetoothGatt> mGattMap;
    private Map<String, Map<ServiceEntity, List<CharacteristicEntity>>> mServicesMap;
    private Map<UuidIdentify, BleNotifyCallback> mNotifyCallbackMap;
    private Map<UuidIdentify, BleReadCallback> mReadCallbackMap;
    private Map<UuidIdentify, OnWriteCallback> mWrtieCallbackMap;
    private List<String> mConnectedDevices;

    public BluetoothGattControllImpl(@NonNull Context context) {
        mContext = context;
        mHandler = new Handler(Looper.getMainLooper());
        mConnectCallbackMap = new ConcurrentHashMap<>();
        mMtuCallbackMap = new ConcurrentHashMap<>();
        mNotifyCallbackMap = new ConcurrentHashMap<>();
        mReadCallbackMap = new ConcurrentHashMap<>();
        mWrtieCallbackMap = new ConcurrentHashMap<>();
        mRssiCallbackMap = new ConcurrentHashMap<>();
        mGattMap = new ConcurrentHashMap<>();
        mServicesMap = new ConcurrentHashMap<>();
        mConnectedDevices = Collections.synchronizedList(new ArrayList<String>());
    }

    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            String address = gatt.getDevice().getAddress();
            Map.Entry<BleDevice, BleConnectCallback> entry = findMapEntry(mConnectCallbackMap, address);
            if (entry == null) {
                return;
            }
            final BleConnectCallback callback = entry.getValue();
            final BleDevice device = entry.getKey();
            switch (newState) {
                case BluetoothProfile.STATE_CONNECTED:
                    //start discovering services, only services are found do we deem
                    //connection is successful
                    gatt.discoverServices();
                    break;
                case BluetoothProfile.STATE_DISCONNECTED:
                    device.connected = false;
                    gatt.close();
                    removeDevice(device);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) {
                                callback.onDisconnect(device);
                            }
                        }
                    });
                    break;
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            String address = gatt.getDevice().getAddress();
            Map.Entry<BleDevice, BleConnectCallback> entry = findMapEntry(mConnectCallbackMap, address);
            if (entry == null) {
                return;
            }

            final Map<ServiceEntity, List<CharacteristicEntity>> servicesInfoMap = new HashMap<>();
            List<BluetoothGattService> gattServices = gatt.getServices();
            for (BluetoothGattService service : gattServices) {
                String uuid = service.getUuid().toString();
                ServiceEntity serviceInfo = new ServiceEntity(uuid);
                List<CharacteristicEntity> charactInfos = new ArrayList<>();
                for (BluetoothGattCharacteristic ch : service.getCharacteristics()) {
                    String chUuid = ch.getUuid().toString();
                    boolean readable = (ch.getProperties() & BluetoothGattCharacteristic.PROPERTY_READ) > 0;
                    boolean writeable = (ch.getProperties() & (BluetoothGattCharacteristic.PROPERTY_WRITE |
                            BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE)) > 0;
                    boolean notify = (ch.getProperties() & BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0;
                    boolean indicate = (ch.getProperties() & BluetoothGattCharacteristic.PROPERTY_INDICATE) > 0;
                    CharacteristicEntity charactInfo = new CharacteristicEntity(chUuid, readable, writeable, notify, indicate);
                    charactInfos.add(charactInfo);
                }
                servicesInfoMap.put(serviceInfo, charactInfos);
            }
            mServicesMap.put(address, servicesInfoMap);

            if (!mConnectedDevices.contains(address)) {
                mConnectedDevices.add(address);
            }

            final BleConnectCallback callback = entry.getValue();
            final BleDevice device = entry.getKey();
            //remove connection timeout message
            mHandler.removeCallbacksAndMessages(address);
            device.connected = true;
            device.connecting = false;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (callback != null) {
                        callback.onConnect(device);
                    }
                }
            });
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            String address = gatt.getDevice().getAddress();
            String serviceUuid = characteristic.getService().getUuid().toString();
            String characteristicUuid = characteristic.getUuid().toString();
            UuidIdentify identify = getUuidIdentifyFromMap(mReadCallbackMap, address, serviceUuid, characteristicUuid);
            if (identify == null) {
                return;
            }
            final BleReadCallback callback = mReadCallbackMap.get(identify);
            final BleDevice device = getBleDeviceFromMap(address, mConnectCallbackMap);
            final byte[] data = characteristic.getValue();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (callback != null) {
                        callback.onRead(data, device);
                    }
                }
            });
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
            String address = gatt.getDevice().getAddress();
            String serviceUuid = characteristic.getService().getUuid().toString();
            String characteristicUuid = characteristic.getUuid().toString();
            UuidIdentify identify = getUuidIdentifyFromMap(mWrtieCallbackMap, address, serviceUuid, characteristicUuid);
            if (identify == null) {
                return;
            }
            final OnWriteCallback callback = mWrtieCallbackMap.get(identify);
            final BleDevice device = getBleDeviceFromMap(address, mConnectCallbackMap);
            final byte[] data = characteristic.getValue();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (callback != null) {
                        callback.writed(data, device);
                    }
                }
            });
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            String address = gatt.getDevice().getAddress();
            String serviceUuid = characteristic.getService().getUuid().toString();
            String characteristicUuid = characteristic.getUuid().toString();
            UuidIdentify identify = getUuidIdentifyFromMap(mNotifyCallbackMap, address, serviceUuid, characteristicUuid);
            if (identify == null) {
                return;
            }
            final BleNotifyCallback callback = mNotifyCallbackMap.get(identify);
            final BleDevice device = getBleDeviceFromMap(address, mConnectCallbackMap);
            final byte[] data = characteristic.getValue();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (callback != null) {
                        callback.onCharacteristicChanged(data, device);
                    }
                }
            });
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                String address = gatt.getDevice().getAddress();
                String serviceUuid = descriptor.getCharacteristic().getService().getUuid().toString();
                final String characteristicUuid = descriptor.getCharacteristic().getUuid().toString();
                UuidIdentify identify = getUuidIdentifyFromMap(mNotifyCallbackMap, address, serviceUuid, characteristicUuid);
                if (identify == null) {
                    return;
                }
                final BleNotifyCallback callback = mNotifyCallbackMap.get(identify);
                final BleDevice device = getBleDeviceFromMap(address, mConnectCallbackMap);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onNotifySuccess(characteristicUuid, device);
                        }
                    }
                });
            }
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, final int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
            String address = gatt.getDevice().getAddress();
            final BleRssiCallback callback = mRssiCallbackMap.get(address);
            final BleDevice device = getBleDeviceFromMap(address, mConnectCallbackMap);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (callback != null) {
                        callback.onRssi(rssi, device);
                    }
                }
            });
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, final int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
            String address = gatt.getDevice().getAddress();
            final BleMtuCallback callback = mMtuCallbackMap.get(address);
            final BleDevice device = getBleDeviceFromMap(address, mConnectCallbackMap);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (callback != null) {
                        callback.onMtuChanged(mtu, device);
                    }
                }
            });
        }
    };

    @Override
    public synchronized void connect(int connectTimeout, final BleDevice device, final BleConnectCallback callback) {
        checkNotNull(callback, BleConnectCallback.class);
        checkNotNull(device, BleDevice.class);
        if (!isBluetoothEnable()) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onStart(false,
                            "Turn on bluetooth before starting connecting device", device);
                }
            });
            return;
        }
        final BleDevice d = getBleDeviceFromMap(device.getDevice().getAddress(), mConnectCallbackMap);
        if (d != null) {
            if (d.connecting || d.connected) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        String info = "";
                        if (d.connecting) {
                            info = "Connection between master device and the target remote device is in progress";
                        } else if (d.connected) {
                            info = "The master device has already connected to this device";
                        }
                        callback.onStart(false, info, d);
                    }
                });
                return;
            }
            if (d != device) {//we tend to use the newest BleDevice object
                mConnectCallbackMap.remove(d);
            }
        }
        mConnectCallbackMap.put(device, callback);

        final BluetoothGatt gatt = device.getDevice().connectGatt(mContext, false, mGattCallback);

        if (gatt != null) {
            device.connecting = true;
            mGattMap.put(device.getDevice().getAddress(), gatt);
            if (connectTimeout > 0) {
                mConnectTimeout = connectTimeout;
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onStart(true, "Start connection success!", device);
                }
            });
            //using sendMessageDelayed() rather than postDelayed() to send connection timeout
            //message just for removing the delayed messgae easily when device has been connected
            Message msg = Message.obtain(mHandler, new Runnable() {
                @Override
                public void run() {
                    device.connecting = false;
                    gatt.close();
                    mGattMap.remove(device.getDevice().getAddress());
                    if (callback != null) {
                        callback.onTimeout(device);
                    }
                }
            });
            msg.obj = device.getDevice().getAddress();
            mHandler.sendMessageDelayed(msg, mConnectTimeout);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onStart(false, "unknown reason", device);
                }
            });
        }
    }

    @Override
    public void disconnect(String address) {
        if (!BluetoothAdapter.checkBluetoothAddress(address)) {
            return;
        }
        BluetoothGatt gatt = mGattMap.get(address);
        if (gatt == null) {
            return;
        }
        gatt.disconnect();
//      refreshDeviceCache(gatt);
        gatt.close();
        //remove connection timeout message if a connection attempt currently is in progress
        mHandler.removeCallbacksAndMessages(address);
        mGattMap.remove(address);
        Map.Entry<BleDevice, BleConnectCallback> entry = findMapEntry(mConnectCallbackMap, address);
        if (entry != null) {
            final BleDevice d = entry.getKey();
            final BleConnectCallback callback = entry.getValue();
            d.connected = false;
            removeDevice(d);
            if (d.connecting) { //break a connection attempt being in progress
                d.connecting = false;
            } else {//break a successful connection
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onDisconnect(d);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void disconnectAll() {
        for (String address : mGattMap.keySet()) {
            disconnect(address);
        }
    }

    @Override
    public void notify(final BleDevice device, String serviceUuid, String notifyUuid, final BleNotifyCallback callback) {
        checkNotNull(callback, BleNotifyCallback.class);
        if (!checkConnection(device, callback)) {
            return;
        }
        BluetoothGatt gatt = mGattMap.get(device.getDevice().getAddress());
        if (!checkUuid(serviceUuid, notifyUuid, gatt, device, callback)) {
            return;
        }

        UuidIdentify identify = getUuidIdentifyFromMap(mNotifyCallbackMap, device.getDevice().getAddress(), serviceUuid, notifyUuid);
        if (identify != null) {
            mNotifyCallbackMap.put(identify, callback);
        } else {
            mNotifyCallbackMap.put(new UuidIdentify(device.getDevice().getAddress(), serviceUuid, notifyUuid), callback);
        }

        BluetoothGattService service = gatt.getService(UUID.fromString(serviceUuid));
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(notifyUuid));
        boolean notify = (characteristic.getProperties() & BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0;
        boolean indicate = (characteristic.getProperties() & BluetoothGattCharacteristic.PROPERTY_INDICATE) > 0;
        if (!notify && !indicate) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER,
                            "this characteristic doesn't support notification or indication", device);
                }
            });
            return;
        }
        if (!enableNotificationOrIndication(gatt, characteristic)) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER, "setting characteristic notification fail", device);
                }
            });
        }
    }

    @Override
    public void cancelNotify(BleDevice device, String serviceUuid, String notifyUuid) {
        UuidIdentify identify = getUuidIdentifyFromMap(mNotifyCallbackMap, device.getDevice().getAddress(), serviceUuid, notifyUuid);
        if (identify != null) {
            mNotifyCallbackMap.remove(identify);
        }
    }

    @Override
    public void read(final BleDevice device, String serviceUuid, String readUuid, final BleReadCallback callback) {
        checkNotNull(callback, BleReadCallback.class);
        if (!checkConnection(device, callback)) {
            return;
        }
        BluetoothGatt gatt = mGattMap.get(device.getDevice().getAddress());
        if (!checkUuid(serviceUuid, readUuid, gatt, device, callback)) {
            return;
        }

        UuidIdentify identify = getUuidIdentifyFromMap(mReadCallbackMap, device.getDevice().getAddress(), serviceUuid, readUuid);
        if (identify != null) {
            mReadCallbackMap.put(identify, callback);
        } else {
            mReadCallbackMap.put(new UuidIdentify(device.getDevice().getAddress(), serviceUuid, readUuid), callback);
        }

        BluetoothGattService service = gatt.getService(UUID.fromString(serviceUuid));
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(readUuid));
        boolean readable = (characteristic.getProperties() & BluetoothGattCharacteristic.PROPERTY_READ) > 0;
        if (!readable) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER, "the characteristic is not readable", device);
                }
            });
            return;
        }
        if (!gatt.readCharacteristic(characteristic)) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER, "read fail because of unknown reason", device);
                }
            });
        }
    }

    @Override
    public void write(final BleDevice device, String serviceUuid, String writeUuid, byte[] data, final OnWriteCallback callback) {
        checkNotNull(callback, OnWriteCallback.class);
        if (!checkConnection(device, callback)) {
            return;
        }
        BluetoothGatt gatt = mGattMap.get(device.getDevice().getAddress());
        if (!checkUuid(serviceUuid, writeUuid, gatt, device, callback)) {
            return;
        }

        UuidIdentify identify = getUuidIdentifyFromMap(mWrtieCallbackMap, device.getDevice().getAddress(), serviceUuid, writeUuid);
        if (identify != null) {
            mWrtieCallbackMap.put(identify, callback);
        } else {
            mWrtieCallbackMap.put(new UuidIdentify(device.getDevice().getAddress(), serviceUuid, writeUuid), callback);
        }

        BluetoothGattService service = gatt.getService(UUID.fromString(serviceUuid));
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(writeUuid));
        boolean writeable = (characteristic.getProperties() & (BluetoothGattCharacteristic.PROPERTY_WRITE |
                BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE)) > 0;
        if (!writeable) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER, "the characteristic is not writeable", device);
                }
            });
            return;
        }
        if (!characteristic.setValue(data) || !gatt.writeCharacteristic(characteristic)) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER, "writed fail because of unknown reason", device);
                }
            });
        }
    }

    @Override
    public void writeByBatch(BleDevice device, final String serviceUuid, final String writeUuid,
                             final byte[] writedData, final int lengthPerPackage, final BleWriteByBatchCallback callback) {
        checkNotNull(callback, BleWriteByBatchCallback.class);
        final List<byte[]> byteList = getBatchData(writedData, lengthPerPackage);
        if (byteList.size() > 0) {
            OnWriteCallback writeCallback = new OnWriteCallback() {
                @Override
                public void writed(byte[] bytes, BleDevice device) {
                    byteList.remove(0);
                    if (byteList.size() != 0) {
                        write(device, serviceUuid, writeUuid, byteList.get(0), this);
                    } else {
                        callback.writeByBatchSuccess(writedData, device);
                    }
                }

                @Override
                public void failure(int failCode, String info, BleDevice device) {
                    callback.failure(failCode, info, device);
                }
            };
            write(device, serviceUuid, writeUuid, byteList.get(0), writeCallback);
        }
    }

    private List<byte[]> getBatchData(byte[] data, int lengthPerPackage) {
        List<byte[]> batchDatas = new ArrayList<>();
        if (data == null || data.length == 0) {
            return batchDatas;
        }
        if (lengthPerPackage < 0) {
            lengthPerPackage = 20;
        }
        int packageNummber = (data.length % lengthPerPackage == 0) ? data.length / lengthPerPackage :
                data.length / lengthPerPackage + 1;
        for (int i = 1; i <= packageNummber; i++) {
            int start = lengthPerPackage * (i - 1);
            int end = lengthPerPackage * i - 1;
            if (i == packageNummber) {
                end = data.length - 1;
            }
            batchDatas.add(getPackageData(data, start, end));
        }
        return batchDatas;
    }

    private byte[] getPackageData(byte[] data, int start, int end) {
        if (start > end || end > data.length - 1) {
            return null;
        }
        byte[] packageData = new byte[end - start + 1];
        for (int i = 0; start <= end; i++, start++) {
            packageData[i] = data[start];
        }
        return packageData;
    }

    @Override
    public void readRssi(final BleDevice device, final BleRssiCallback callback) {
        checkNotNull(callback, BleRssiCallback.class);
        if (!checkConnection(device, callback)) {
            return;
        }
        mRssiCallbackMap.put(device.getDevice().getAddress(), callback);
        BluetoothGatt gatt = mGattMap.get(device.getDevice().getAddress());
        if (!gatt.readRemoteRssi()) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER, "fail to read rssi because of unknown reason", device);
                }
            });
        }
    }

    @SuppressWarnings("NewApi")
    @Override
    public void setMtu(final BleDevice device, int mtu, final BleMtuCallback callback) {
        checkNotNull(callback, BleMtuCallback.class);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER,
                            "The minimum android api version which setMtu supports is 21", device);
                }
            });
            return;
        }
        if (!checkConnection(device, callback)) {
            return;
        }
        mMtuCallbackMap.put(device.getDevice().getAddress(), callback);
        BluetoothGatt gatt = mGattMap.get(device.getDevice().getAddress());
        if (mtu < 23) {
            mtu = 23;
        }
        if (!gatt.requestMtu(mtu)) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER, "fail to read rssi because of unknown reason", device);
                }
            });
        }
    }

    @Override
    public List<BleDevice> getConnectedDevices() {
        List<BleDevice> deviceList = new ArrayList<>();
        for (String address : mConnectedDevices) {
            BleDevice d = getBleDeviceFromMap(address, mConnectCallbackMap);
            deviceList.add(d);
        }
        return deviceList;
    }

    @Override
    public Map<ServiceEntity, List<CharacteristicEntity>> getDeviceServices(BleDevice device) {
        checkNotNull(device, BleDevice.class);
        return mServicesMap.get(device.getDevice().getAddress());
    }

    @Override
    public BluetoothGatt getBluetoothGatt(String address) {
        return mGattMap.get(address);
    }

    @Override
    public void onDestory() {
        mHandler.removeCallbacksAndMessages(null);
        disconnectAll();
        clearAllCallbacks();
    }

    private void checkNotNull(Object object, Class<?> clasz) {
        if (object == null) {
            String claszSimpleName = clasz.getSimpleName();
            throw new IllegalArgumentException(claszSimpleName + " is null");
        }
    }

    private boolean checkConnection(final BleDevice device, final BleCallback callback) {
        checkNotNull(device, BleDevice.class);
        if (!mConnectedDevices.contains(device.getDevice().getAddress())) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_DISCONNECTED,
                            "Connection between master device and target remote device has not been established yet", device);
                }
            });
            return false;
        }
        return true;
    }

    private boolean checkUuid(String serviceUuid, String charUuid, BluetoothGatt gatt,
                              final BleDevice device, final BleCallback callback) {
        BluetoothGattService service = gatt.getService(UUID.fromString(serviceUuid));
        if (service == null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER,
                            "the remote device doesn't contain this service uuid", device);
                }
            });
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(charUuid));
        if (characteristic == null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.failure(BleCallback.FAIL_OTHER,
                            "the service of remote device doesn't contain this characteristic uuid", device);
                }
            });
            return false;
        }
        return true;
    }

    private <T> Map.Entry<BleDevice, T> findMapEntry(Map<BleDevice, T> map, String address) {
        if (map == null || !BluetoothAdapter.checkBluetoothAddress(address)) {
            return null;
        }
        Set<Map.Entry<BleDevice, T>> set = map.entrySet();
        for (Map.Entry<BleDevice, T> entry : set) {
            if (entry.getKey().getDevice().getAddress().equals(address)) {
                return entry;
            }
        }
        return null;
    }

    private <T> BleDevice getBleDeviceFromMap(String address, Map<BleDevice, T> map) {
        Map.Entry<BleDevice, T> entry = findMapEntry(map, address);
        if (entry != null) {
            return entry.getKey();
        }
        return null;
    }

    private <T> UuidIdentify getUuidIdentifyFromMap(Map<UuidIdentify, T> map, String address,
                                                    String serviceUuid, String characteristicUuid) {
        if (TextUtils.isEmpty(address) || TextUtils.isEmpty(serviceUuid) || TextUtils.isEmpty(characteristicUuid)) {
            return null;
        }
        for (UuidIdentify ui : map.keySet()) {
            if (ui.address.equalsIgnoreCase(address)
                    && ui.characteristicUuid.equalsIgnoreCase(characteristicUuid)
                    && ui.serviceUuid.equalsIgnoreCase(serviceUuid)) {
                return ui;
            }
        }
        return null;
    }

    private void removeDevice(BleDevice device) {
        mConnectCallbackMap.remove(device);
        mMtuCallbackMap.remove(device.getDevice().getAddress());
        mRssiCallbackMap.remove(device.getDevice().getAddress());
        mGattMap.remove(device.getDevice().getAddress());
        mServicesMap.remove(device.getDevice().getAddress());
        mConnectedDevices.remove(device.getDevice().getAddress());
        removeUuidIdentifyMap(device.getDevice().getAddress(), mReadCallbackMap);
        removeUuidIdentifyMap(device.getDevice().getAddress(), mWrtieCallbackMap);
        removeUuidIdentifyMap(device.getDevice().getAddress(), mNotifyCallbackMap);
    }

    private <T> void removeUuidIdentifyMap(String address, Map<UuidIdentify, T> map) {
        for (UuidIdentify ui : map.keySet()) {
            if (ui.address.equals(address)) {
                map.remove(ui);
            }
        }
    }

    private void clearAllCallbacks() {
        mConnectCallbackMap.clear();
        mMtuCallbackMap.clear();
        mNotifyCallbackMap.clear();
        mReadCallbackMap.clear();
        mWrtieCallbackMap.clear();
        mRssiCallbackMap.clear();
        mGattMap.clear();
        mServicesMap.clear();
        mConnectedDevices.clear();
    }

    /**
     * Clears the ble device's internal cache and forces a refresh of the services from the
     * ble device.
     */
    private boolean refreshDeviceCache(BluetoothGatt gatt) {
        try {
            Method refresh = BluetoothGatt.class.getMethod("refresh");
            return refresh != null && (boolean) refresh.invoke(gatt);
        } catch (Exception e) {
            LogUtil.i("encounter an exception while refreshing device cache: " + e.getMessage());
            return false;
        }
    }

    private boolean enableNotificationOrIndication(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        if (characteristic == null || !gatt.setCharacteristicNotification(characteristic, true)) {
            return false;
        }
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(CHARACTERISTIC_CONFIG));
        if (descriptor != null) {
            boolean notify = (characteristic.getProperties() & BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0;
            boolean indicate = (characteristic.getProperties() & BluetoothGattCharacteristic.PROPERTY_INDICATE) > 0;
            if (notify) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            }
            if (indicate) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            }
            return gatt.writeDescriptor(descriptor);
        }
        return true;
    }

    private boolean isBluetoothEnable() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        return adapter != null && adapter.isEnabled();
    }

    private final class UuidIdentify {
        String address;
        String serviceUuid;
        String characteristicUuid;

        UuidIdentify(String address, String serviceUuid, String characteristicUuid) {
            this.address = address;
            this.serviceUuid = serviceUuid;
            this.characteristicUuid = characteristicUuid;
        }
    }
}
