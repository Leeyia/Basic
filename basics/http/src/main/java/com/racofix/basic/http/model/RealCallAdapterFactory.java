package com.racofix.basic.http.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class RealCallAdapterFactory extends CallAdapter.Factory {

    public static RealCallAdapterFactory create() {
        return new RealCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

        //返回值必须是RealCall并且带有泛型（参数类型），根据APIService接口中的方法返回值，确定returnType
        //如 RealCall<String> getCategories();，那确定returnType就是RealCall<String>
        if (getRawType(returnType) != RealCall.class) {
            return null;
        }
        final Type callReturnType = getParameterUpperBound(0, (ParameterizedType) returnType);
        return new CallAdapter<Object, RealCall<Object>>() {
            @Override
            public Type responseType() {
                return callReturnType;
            }

            @Override
            public RealCall<Object> adapt(Call<Object> rawCall) {
                return new RealCall<>(rawCall);
            }
        };
    }
}
