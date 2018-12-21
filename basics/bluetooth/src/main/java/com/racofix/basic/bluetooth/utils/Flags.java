package com.racofix.basic.bluetooth.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Flags for changing global SDK parameters. Intended for enabling/disabling
 * experimental features.
 */
public enum Flags {
    FORCE_OLD_SCANNING_API("force_old_scanning_api", false),
    DISABLE_BATCH_SCANNING("disable_batch_scanning", true),
    DISABLE_HARDWARE_FILTERING("disable_hardware_filtering", false),
    DISABLE_SIGNAL_FILTERING("disable_signal_filtering", false),
    DISABLE_FIRMWARE_CACHING("disable_firmware_caching", false),
    ENABLE_FIRMWARE_OVERRIDE("enable_firmware_override", false),
    DISABLE_BT_RECOVERY("disable_bt_recovery", false),;

    private final String name;
    private Boolean value;
    private boolean defaultValue;

    Flags(String name, boolean defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public void set(boolean value) {
        this.value = value;
    }

    public boolean isSet(Context context) {
        if (value != null) {
            return value;
        }
        try {
            ApplicationInfo appInfo =
                    context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                return value = appInfo.metaData.getBoolean(name, defaultValue);
            }
        } catch (PackageManager.NameNotFoundException e) {
            return value = defaultValue;
        }
        return value = defaultValue;
    }
}

