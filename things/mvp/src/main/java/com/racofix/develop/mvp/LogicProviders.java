package com.racofix.develop.mvp;

import com.racofix.develop.mvp.LogicI;
import com.racofix.develop.mvp.annotation.Logic;

public class LogicProviders {

    public static LogicI init(Class<?> clazz) {
        try {
            Logic annotation = clazz.getAnnotation(Logic.class);
            if (annotation != null)
                return (LogicI) annotation.value().newInstance();
            return null;
        } catch (InstantiationException e) {
//            throw new RuntimeException("Cannot create an instance of " + clazz, e);
            return null;
        } catch (IllegalAccessException e) {
//            throw new RuntimeException("Cannot create an instance of " + clazz, e);
            return null;
        }
    }
}
