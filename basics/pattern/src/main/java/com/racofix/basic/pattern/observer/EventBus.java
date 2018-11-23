package com.racofix.basic.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * EventBus - 被观察者/主题
 *
 * @param <T> 观察对象
 */
public class EventBus<T> {

    private static volatile EventBus defaultInstance;
    private List<Subscriber> subscribers = new ArrayList<>();

    public static <T> EventBus<T> getDefault() {
        if (defaultInstance == null) {
            //防止多线程同同时访问, 进行等待-
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }

    private EventBus() {
    }

    public void register(Subscriber observer) {
        if (observer == null) {
            throw new NullPointerException("observer == null");
        }

        synchronized (this) {
            if (!subscribers.contains(observer))
                this.subscribers.add(observer);
        }
    }

    public synchronized void unregister(Subscriber<T> observer) {
        this.subscribers.remove(observer);
    }

    public void notifyObservers(T item) {
        for (Subscriber<T> subscriber : subscribers) {
            subscriber.onUpdate(this, item);
        }
    }

}
