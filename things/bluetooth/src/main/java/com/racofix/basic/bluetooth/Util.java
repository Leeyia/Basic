package com.racofix.basic.bluetooth;

import android.support.annotation.Nullable;

final class Util {
    
    static <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message + "is null");
        }
        return object;
    }
}
