package com.meikoz.core.manage.interfacee;

import android.widget.ImageView;

/**
 * @User: 蜡笔小新
 * @date: 16-12-7
 * @GitHub: https://github.com/meikoz
 */

public interface ImageLoadInterface {
    void loadNet(ImageView target, String url);

    void loadNet(ImageView target, String url, int animal);

    void loadDrawable(ImageView target, int resId);

    void loadDrawable(ImageView target, int resId, int animal);
}
