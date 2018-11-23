package com.racofix.basic.pattern.singleton;

public class EventBus {

    private static volatile EventBus defaultInstance;

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            //防止多线程同同时访问, 进行等待-
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }
}
