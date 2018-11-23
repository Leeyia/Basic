package com.racofix.basic.pattern.observer;

/**
 * Subscriber - 观察者/订阅者
 *
 * @param <T> 观察对象
 */
public interface Subscriber<T> {
    void onUpdate(EventBus<T> observable, T item);
}
