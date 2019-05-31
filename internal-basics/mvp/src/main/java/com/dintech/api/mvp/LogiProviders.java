package com.dintech.api.mvp;

import android.util.Log;

public final class LogiProviders {

    private LogiProviders() {
    }

    public static BaseLogi init(Class<?> clazz) {
        try {
            Logi annotation = clazz.getAnnotation(Logi.class);
            if (annotation != null)
                return (BaseLogi) annotation.value().newInstance();
            return null;
        } catch (InstantiationException e) {
            Log.e(LogiProviders.class.getSimpleName(), "Cannot create an instance of " + clazz, e);
            return null;
        } catch (IllegalAccessException e) {
            Log.e(LogiProviders.class.getSimpleName(), "Cannot create an instance of " + clazz, e);
            return null;
        }
    }
}
