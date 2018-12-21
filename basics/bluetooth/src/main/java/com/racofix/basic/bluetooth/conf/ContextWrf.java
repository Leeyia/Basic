package com.racofix.basic.bluetooth.conf;

import android.content.Context;

import com.racofix.basic.bluetooth.utils.Preconditions;

import java.lang.ref.WeakReference;

public class ContextWrf {

    private static WeakReference<Context> wrf;

    public static void initialize(Context context) {
        ContextWrf.wrf = new WeakReference<>(context);
    }

    public static Context get() {
        Preconditions.checkNotNull(wrf, "Application not BluetoothKit.initialize(this)");
        Preconditions.checkNotNull(wrf.get(), "Application not BluetoothKit.initialize(this)");
        return wrf.get();
    }
}
