package com.android.core.control;

import android.widget.Toast;

import com.android.core.MainApp;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-02 17:56
 * @GitHub: https://github.com/meikoz
 */
public class ToastUtil {

    private static Toast mToast;

    public static void show(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(MainApp.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void show(int msg) {
        if (mToast == null) {
            mToast = Toast.makeText(MainApp.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
