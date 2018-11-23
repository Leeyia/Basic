package com.racofix.basic.mvp;

import android.util.Log;

import com.racofix.basic.mvp.annotation.Implement;

public final class LogicProviders {

    private LogicProviders() {
    }

    public static BaseLogic init(Class<?> clazz) {
        try {
            Implement annotation = clazz.getAnnotation(Implement.class);
            if (annotation != null)
                return (BaseLogic) annotation.value().newInstance();
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
