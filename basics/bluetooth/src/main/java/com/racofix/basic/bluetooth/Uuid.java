package com.racofix.basic.bluetooth;

import android.os.ParcelUuid;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

/**
 * #Ran.
 * #from(https://github.com/Leeyia/Bluetooth)
 * #2018/5/24.
 */
public class Uuid {

    public static final ParcelUuid AudioSink = ParcelUuid.fromString("0000110B-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid AudioSource = ParcelUuid.fromString("0000110A-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid AdvAudioDist = ParcelUuid.fromString("0000110D-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid HSP = ParcelUuid.fromString("00001108-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid HSP_AG = ParcelUuid.fromString("00001112-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid Handsfree = ParcelUuid.fromString("0000111E-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid Handsfree_AG = ParcelUuid.fromString("0000111F-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid AvrcpController = ParcelUuid.fromString("0000110E-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid AvrcpTarget = ParcelUuid.fromString("0000110C-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid ObexObjectPush = ParcelUuid.fromString("00001105-0000-1000-8000-00805f9b34fb");
    public static final ParcelUuid Hid = ParcelUuid.fromString("00001124-0000-1000-8000-00805f9b34fb");
    public static final ParcelUuid Hogp = ParcelUuid.fromString("00001812-0000-1000-8000-00805f9b34fb");
    public static final ParcelUuid PANU = ParcelUuid.fromString("00001115-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid NAP = ParcelUuid.fromString("00001116-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid BNEP = ParcelUuid.fromString("0000000f-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid PBAP_PCE = ParcelUuid.fromString("0000112e-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid PBAP_PSE = ParcelUuid.fromString("0000112f-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid MAP = ParcelUuid.fromString("00001134-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid MNS = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid MAS = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;
    public static final int UUID_BYTES_128_BIT = 16;
    public static final ParcelUuid[] RESERVED_UUIDS;

    Uuid() {
    }

    public static boolean isAudioSource(ParcelUuid uuid) {
        return uuid.equals(AudioSource);
    }

    public static boolean isAudioSink(ParcelUuid uuid) {
        return uuid.equals(AudioSink);
    }

    public static boolean isAdvAudioDist(ParcelUuid uuid) {
        return uuid.equals(AdvAudioDist);
    }

    public static boolean isHandsfree(ParcelUuid uuid) {
        return uuid.equals(Handsfree);
    }

    public static boolean isHeadset(ParcelUuid uuid) {
        return uuid.equals(HSP);
    }

    public static boolean isAvrcpController(ParcelUuid uuid) {
        return uuid.equals(AvrcpController);
    }

    public static boolean isAvrcpTarget(ParcelUuid uuid) {
        return uuid.equals(AvrcpTarget);
    }

    public static boolean isInputDevice(ParcelUuid uuid) {
        return uuid.equals(Hid);
    }

    public static boolean isPanu(ParcelUuid uuid) {
        return uuid.equals(PANU);
    }

    public static boolean isNap(ParcelUuid uuid) {
        return uuid.equals(NAP);
    }

    public static boolean isBnep(ParcelUuid uuid) {
        return uuid.equals(BNEP);
    }

    public static boolean isMap(ParcelUuid uuid) {
        return uuid.equals(MAP);
    }

    public static boolean isMns(ParcelUuid uuid) {
        return uuid.equals(MNS);
    }

    public static boolean isMas(ParcelUuid uuid) {
        return uuid.equals(MAS);
    }

    public static boolean isUuidPresent(ParcelUuid[] uuidArray, ParcelUuid uuid) {
        if ((uuidArray == null || uuidArray.length == 0) && uuid == null) {
            return true;
        } else if (uuidArray == null) {
            return false;
        } else {
            ParcelUuid[] var2 = uuidArray;
            int var3 = uuidArray.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                ParcelUuid element = var2[var4];
                if (element.equals(uuid)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean containsAnyUuid(ParcelUuid[] uuidA, ParcelUuid[] uuidB) {
        if (uuidA == null && uuidB == null) {
            return true;
        } else if (uuidA == null) {
            return uuidB.length == 0;
        } else if (uuidB == null) {
            return uuidA.length == 0;
        } else {
            HashSet uuidSet = new HashSet(Arrays.asList(uuidA));
            ParcelUuid[] var3 = uuidB;
            int var4 = uuidB.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                ParcelUuid uuid = var3[var5];
                if (uuidSet.contains(uuid)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean containsAllUuids(ParcelUuid[] uuidA, ParcelUuid[] uuidB) {
        if (uuidA == null && uuidB == null) {
            return true;
        } else if (uuidA == null) {
            return uuidB.length == 0;
        } else if (uuidB == null) {
            return true;
        } else {
            HashSet uuidSet = new HashSet(Arrays.asList(uuidA));
            ParcelUuid[] var3 = uuidB;
            int var4 = uuidB.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                ParcelUuid uuid = var3[var5];
                if (!uuidSet.contains(uuid)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static int getServiceIdentifierFromParcelUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        long value = (uuid.getMostSignificantBits() & 281470681743360L) >>> 32;
        return (int) value;
    }

    public static ParcelUuid parseUuidFrom(byte[] uuidBytes) {
        if (uuidBytes == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        } else {
            int length = uuidBytes.length;
            if (length != 2 && length != 4 && length != 16) {
                throw new IllegalArgumentException("uuidBytes length invalid - " + length);
            } else if (length == 16) {
                ByteBuffer shortUuid1 = ByteBuffer.wrap(uuidBytes).order(ByteOrder.LITTLE_ENDIAN);
                long msb = shortUuid1.getLong(8);
                long lsb = shortUuid1.getLong(0);
                return new ParcelUuid(new UUID(msb, lsb));
            } else {
                long shortUuid;
                if (length == 2) {
                    shortUuid = (long) (uuidBytes[0] & 255);
                    shortUuid += (long) ((uuidBytes[1] & 255) << 8);
                } else {
                    shortUuid = (long) (uuidBytes[0] & 255);
                    shortUuid += (long) ((uuidBytes[1] & 255) << 8);
                    shortUuid += (long) ((uuidBytes[2] & 255) << 16);
                    shortUuid += (long) ((uuidBytes[3] & 255) << 24);
                }

                long msb1 = BASE_UUID.getUuid().getMostSignificantBits() + (shortUuid << 32);
                long lsb1 = BASE_UUID.getUuid().getLeastSignificantBits();
                return new ParcelUuid(new UUID(msb1, lsb1));
            }
        }
    }

    public static boolean is16BitUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() != BASE_UUID.getUuid().getLeastSignificantBits() ? false : (uuid.getMostSignificantBits() & -281470681743361L) == 4096L;
    }

    public static boolean is32BitUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() != BASE_UUID.getUuid().getLeastSignificantBits() ? false : (is16BitUuid(parcelUuid) ? false : (uuid.getMostSignificantBits() & 4294967295L) == 4096L);
    }

    static {
        RESERVED_UUIDS = new ParcelUuid[]{AudioSink, AudioSource, AdvAudioDist, HSP, Handsfree, AvrcpController, AvrcpTarget, ObexObjectPush, PANU, NAP, MAP, MNS, MAS};
    }
}