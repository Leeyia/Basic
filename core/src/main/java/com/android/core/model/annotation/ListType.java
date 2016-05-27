package com.android.core.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedList;

/**
 * Created by snower on 16-1-13.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListType {
    Class list() default LinkedList.class;
    Class value();
}
