package com.racofix.basic.http;

import java.util.List;

public interface GsonService {

    <T> T parseObject(String json, Class<T> clazz);

    <T> List<T> parseArray(String json, Class<T> clazz);

    String toJsonString(Object instance);

    class Factory {

        private static GsonService instance;

        public static GsonService create() {
            if (instance == null) {
                synchronized (Factory.class) {
                    if (instance == null)
                        instance = new GsonServiceImpl();
                }
            }
            return instance;
        }
    }
}
