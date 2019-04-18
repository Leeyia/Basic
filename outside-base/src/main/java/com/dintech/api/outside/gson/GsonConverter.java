package com.dintech.api.outside.gson;

import java.util.List;

public interface GsonConverter {

    <T> T json2Object(String json, Class<T> clazz);

    <T> List<T> json2Array(String json, Class<T> clazz);

    String object2Json(Object instance);
}
