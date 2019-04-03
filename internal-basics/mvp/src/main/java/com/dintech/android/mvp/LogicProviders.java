package com.dintech.android.mvp;

import android.util.Log;

import com.dintech.android.mvp.annotation.Implement;

public final class LogicProviders {

    private LogicProviders() {
    }

    public static Logic init(Class<?> clazz) {
        try {
            Implement annotation = clazz.getAnnotation(Implement.class);
            if (annotation != null)
                return (Logic) annotation.value().newInstance();
            return null;
        } catch (InstantiationException e) {
            Log.e(LogicProviders.class.getSimpleName(), "Cannot create an instance of " + clazz, e);
            return null;
        } catch (IllegalAccessException e) {
            Log.e(LogicProviders.class.getSimpleName(), "Cannot create an instance of " + clazz, e);
            return null;
        }
    }
}
