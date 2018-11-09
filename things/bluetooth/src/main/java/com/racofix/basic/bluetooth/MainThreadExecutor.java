package com.racofix.basic.bluetooth;

import android.os.Handler;
import android.os.Looper;

public class MainThreadExecutor {

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void execute(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            executeDelay(runnable, 0);
        }
    }

    public static void executeDelay(Runnable runnable, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        mHandler.postDelayed(runnable, delayMillis);
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
