package com.dintech.android.outside.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

class GsonConverterImpl implements GsonConverter {

    private final Gson gson;

    GsonConverterImpl() {
        this.gson = new Gson();
    }

    @Override
    public <T> T json2Object(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    @Override
    public <T> List<T> json2Array(String json, Class<T> clazz) {
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    @Override
    public String object2Json(Object instance) {
        return gson.toJson(instance);
    }
}
