package com.dintech.android.outside.bluetooth;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*Bluetooth Proxy*/
public class BluetoothKit {

    private static Operation mBluetoothKit;

    public static Operation getDefalut() {
        return getDefalut(new DefaultOperationImpl());
    }

    public static Operation getDefalut(final Operation operation) {
        if (mBluetoothKit == null) synchronized (BluetoothKit.class) {
            if (mBluetoothKit == null) {
                Log.d("BluetoothKit", "对象被实例化");
                mBluetoothKit = (Operation) Proxy.newProxyInstance(operation.getClass().getClassLoader(), operation.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        Log.d("BluetoothKit", "方法调用前");
                        Object invoke = method.invoke(operation, objects);
                        Log.d("BluetoothKit", "方法调用后");
                        return invoke;
                    }
                });
            }
        }
        return mBluetoothKit;
    }

    private BluetoothKit() {
    }
}
