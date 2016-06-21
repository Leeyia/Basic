package com.android.core.control;

import android.os.Handler;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-21 13:59
 * @GitHub: https://github.com/meikoz
 */
public class HandlerTip {

    private static HandlerTip mDelayded = new HandlerTip();
    private Handler handler;

    public HandlerTip() {
        handler = new Handler();
    }


    public static HandlerTip getInstance() {
        return mDelayded;
    }

    public Handler getHandler() {
        return handler;
    }

    public void postDelayed(int time, final HandlerCallback call) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                call.postDelayed();
            }
        }, time);
    }

    public interface HandlerCallback {
        void postDelayed();
    }
}
