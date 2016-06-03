package com.android.core.control;

import com.android.core.MainApp;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-02 17:56
 * @GitHub: https://github.com/meikoz
 */
public class Toast {

    private static android.widget.Toast mToast;

    public static void show(String msg) {
        int duration = android.widget.Toast.LENGTH_SHORT;
        if (msg.length() > 10) {
            duration = android.widget.Toast.LENGTH_LONG;
        }
        mToast = android.widget.Toast.makeText(MainApp.getContext(), msg, duration);
        mToast.show();
    }

    public static void show(int msg) {
        int duration = android.widget.Toast.LENGTH_SHORT;
        mToast = android.widget.Toast.makeText(MainApp.getContext(), msg, duration);
        mToast.show();
    }
}
