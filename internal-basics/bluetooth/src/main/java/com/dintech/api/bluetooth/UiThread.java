package com.dintech.api.bluetooth;

import android.os.Handler;
import android.os.Looper;

public class UiThread {

    private Handler mHandler;
    private static UiThread mUiThread;

    private UiThread() {
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public static UiThread getInstance() {
        if (mUiThread == null) synchronized (UiThread.class) {
            if (mUiThread == null) mUiThread = new UiThread();
        }
        return mUiThread;
    }

    public void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            mHandler.post(runnable);
        } else {
            runnable.run();
        }
    }
}
