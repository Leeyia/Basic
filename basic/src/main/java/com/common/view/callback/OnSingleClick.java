package com.common.view.callback;

import android.view.View;

/**
 * author meikoz on 2016/4/18.
 * email  meikoz@126.com
 */
public abstract class OnSingleClick implements View.OnClickListener{
    public static long lastTime;

    public abstract void singleClick(View v);

    @Override
    public void onClick(View v) {
        if (onDoubClick()) {
            return;
        }
        singleClick(v);
    }

    public boolean onDoubClick() {
        boolean flag = false;
        long time = System.currentTimeMillis() - lastTime;

        if (time < 500) {
            flag = true;
        }
        lastTime = System.currentTimeMillis();
        return flag;
    }
}
