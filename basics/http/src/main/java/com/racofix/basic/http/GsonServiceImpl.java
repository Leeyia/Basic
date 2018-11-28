package com.racofix.basic.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

final class GsonServiceImpl implements GsonService {

    private Gson gson;

    public GsonServiceImpl() {
        if (this.gson == null)
            this.gson = new Gson();
    }

    @Override
    public <T> T parseObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    @Override
    public <T> List<T> parseArray(String json, Class<T> clazz) {
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    @Override
    public String toJsonString(Object instance) {
        return gson.toJson(instance);
    }
}
