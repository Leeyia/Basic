package com.dintech.api.outside.gson;

import java.util.List;

/*
 * 代理设计模式
 * - 更容易扩展
 * 静态工厂方法模式
 * - 屏蔽返回值的具体实现类
 * */
public class GsonConverterFactory implements GsonConverter {

    private final GsonConverter converter;
    private static GsonConverter INSTANCE;

    public static GsonConverter getDefalut() {
        return getDefalut(new GsonConverterImpl());
    }

    public static GsonConverter getDefalut(GsonConverter converter) {
        if (INSTANCE == null) synchronized (GsonConverterFactory.class) {
            if (INSTANCE == null) INSTANCE = new GsonConverterFactory(converter);
        }
        return INSTANCE;
    }

    private GsonConverterFactory(GsonConverter converter) {
        this.converter = converter;
    }

    @Override
    public <T> T json2Object(String json, Class<T> clazz) {
        return converter.json2Object(json, clazz);
    }

    @Override
    public <T> List<T> json2Array(String json, Class<T> clazz) {
        return converter.json2Array(json, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return converter.object2Json(instance);
    }
}
