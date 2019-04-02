package com.dintech.architecture.http;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NetworkProxy implements InvocationHandler {

    private Object mTarget; // 目标对象

    public NetworkProxy(Object target) {
        this.mTarget = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println("权限校验");
        Object invoke = method.invoke(mTarget, args);
        System.out.println("日志记录");
        return invoke; // 返回的是代理对象
    }
}
