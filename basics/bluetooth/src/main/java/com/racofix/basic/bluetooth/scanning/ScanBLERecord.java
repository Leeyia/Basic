package com.racofix.basic.bluetooth.scanning;

import android.os.ParcelUuid;
import android.util.SparseArray;

import com.racofix.basic.bluetooth.Uuid;
import com.racofix.basic.bluetooth.utils.L;
import com.racofix.basic.bluetooth.utils.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * #Ran.
 * #from(https://github.com/Leeyia/Bluetooth)
 * #2018/5/24.
 */

public final class ScanBLERecord {

    private static final String TAG = "ScanBLERecord";
    private static final int DATA_TYPE_FLAGS = 1;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_PARTIAL = 2;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_COMPLETE = 3;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_PARTIAL = 4;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_COMPLETE = 5;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_PARTIAL = 6;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_COMPLETE = 7;
    private static final int DATA_TYPE_LOCAL_NAME_SHORT = 8;
    private static final int DATA_TYPE_LOCAL_NAME_COMPLETE = 9;
    private static final int DATA_TYPE_TX_POWER_LEVEL = 10;
    private static final int DATA_TYPE_SERVICE_DATA = 22;
    private static final int DATA_TYPE_MANUFACTURER_SPECIFIC_DATA = 255;
    private final int mAdvertiseFlags;
    private final List<ParcelUuid> mServiceUuids;
    private final SparseArray<byte[]> mManufacturerSpecificData;
    private final Map<ParcelUuid, byte[]> mServiceData;
    private final int mTxPowerLevel;
    private final String mDeviceName;
    private final byte[] mBytes;

    public int getAdvertiseFlags() {
        return this.mAdvertiseFlags;
    }

    public List<ParcelUuid> getServiceUuids() {
        return this.mServiceUuids;
    }

    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.mManufacturerSpecificData;
    }

    public byte[] getManufacturerSpecificData(int manufacturerId) {
        return (byte[]) this.mManufacturerSpecificData.get(manufacturerId);
    }

    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.mServiceData;
    }

    public byte[] getServiceData(ParcelUuid serviceDataUuid) {
        return serviceDataUuid == null ? null : (byte[]) this.mServiceData.get(serviceDataUuid);
    }

    public int getTxPowerLevel() {
        return this.mTxPowerLevel;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public byte[] getBytes() {
        return this.mBytes;
    }

    public ScanBLERecord(List<ParcelUuid> serviceUuids, SparseArray<byte[]> manufacturerData, Map<ParcelUuid, byte[]> serviceData, int advertiseFlags, int txPowerLevel, String localName, byte[] bytes) {
        this.mServiceUuids = serviceUuids;
        this.mManufacturerSpecificData = manufacturerData;
        this.mServiceData = serviceData;
        this.mDeviceName = localName;
        this.mAdvertiseFlags = advertiseFlags;
        this.mTxPowerLevel = txPowerLevel;
        this.mBytes = bytes;
    }

    public static ScanBLERecord parseFromBytes(byte[] ScanBLERecord) {
        if (ScanBLERecord == null) {
            return null;
        } else {
            int currentPos = 0;
            int advertiseFlag = -1;
            List<ParcelUuid> serviceUuids = new ArrayList<>();
            String localName = null;
            int txPowerLevel = -2147483648;
            SparseArray<byte[]> manufacturerData = new SparseArray<>();
            Map<ParcelUuid, byte[]> serviceData = new HashMap<>();

            try {
                int dataLength;
                for (; currentPos < ScanBLERecord.length; currentPos += dataLength) {
                    int e = ScanBLERecord[currentPos++] & 255;
                    if (e == 0) {
                        break;
                    }

                    dataLength = e - 1;
                    int fieldType = ScanBLERecord[currentPos++] & 255;
                    switch (fieldType) {
                        case 1:
                            advertiseFlag = ScanBLERecord[currentPos] & 255;
                            break;
                        case 2:
                        case 3:
                            parseServiceUuid(ScanBLERecord, currentPos, dataLength, 2, serviceUuids);
                            break;
                        case 4:
                        case 5:
                            parseServiceUuid(ScanBLERecord, currentPos, dataLength, 4, serviceUuids);
                            break;
                        case 6:
                        case 7:
                            parseServiceUuid(ScanBLERecord, currentPos, dataLength, 16, serviceUuids);
                            break;
                        case 8:
                        case 9:
                            localName = new String(extractBytes(ScanBLERecord, currentPos, dataLength));
                            break;
                        case 10:
                            txPowerLevel = ScanBLERecord[currentPos];
                            break;
                        case 22:
                            byte serviceUuidLength = 2;
                            byte[] serviceDataUuidBytes = extractBytes(ScanBLERecord, currentPos, serviceUuidLength);
                            ParcelUuid serviceDataUuid = Uuid.parseUuidFrom(serviceDataUuidBytes);
                            byte[] serviceDataArray = extractBytes(ScanBLERecord, currentPos + serviceUuidLength, dataLength - serviceUuidLength);
                            serviceData.put(serviceDataUuid, serviceDataArray);
                            break;
                        case 255:
                            int manufacturerId = ((ScanBLERecord[currentPos + 1] & 255) << 8) + (ScanBLERecord[currentPos] & 255);
                            byte[] manufacturerDataBytes = extractBytes(ScanBLERecord, currentPos + 2, dataLength - 2);
                            manufacturerData.put(manufacturerId, manufacturerDataBytes);
                    }
                }

                if (serviceUuids.isEmpty()) {
                    serviceUuids = null;
                }

                return new ScanBLERecord(serviceUuids, manufacturerData, serviceData, advertiseFlag, txPowerLevel, localName, ScanBLERecord);
            } catch (Exception var17) {
                L.e("ScanBLERecord: unable to parse scan record: " + Arrays.toString(ScanBLERecord));
                return new ScanBLERecord((List) null, (SparseArray) null, (Map) null, -1, -2147483648, (String) null, ScanBLERecord);
            }
        }
    }

    public String toString() {
        return "ScanBLERecord [mAdvertiseFlags=" + this.mAdvertiseFlags + ", mServiceUuids=" + this.mServiceUuids + ", mManufacturerSpecificData=" + Objects.toString(this.mManufacturerSpecificData) + ", mServiceData=" + Objects.toString(this.mServiceData) + ", mTxPowerLevel=" + this.mTxPowerLevel + ", mDeviceName=" + this.mDeviceName + "]";
    }

    private static int parseServiceUuid(byte[] ScanBLERecord, int currentPos, int dataLength, int uuidLength, List<ParcelUuid> serviceUuids) {
        while (dataLength > 0) {
            byte[] uuidBytes = extractBytes(ScanBLERecord, currentPos, uuidLength);
            serviceUuids.add(Uuid.parseUuidFrom(uuidBytes));
            dataLength -= uuidLength;
            currentPos += uuidLength;
        }

        return currentPos;
    }

    private static byte[] extractBytes(byte[] ScanBLERecord, int start, int length) {
        byte[] bytes = new byte[length];
        System.arraycopy(ScanBLERecord, start, bytes, 0, length);
        return bytes;
    }
}
