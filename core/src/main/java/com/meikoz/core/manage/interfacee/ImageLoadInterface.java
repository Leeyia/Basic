package com.meikoz.core.manage.interfacee;

import android.widget.ImageView;

public interface ImageLoadInterface {
    void loadNet(ImageView target, String url);

    void loadNet(ImageView target, String url, int animal);

    void loadDrawable(ImageView target, int resId);

    void loadDrawable(ImageView target, int resId, int animal);
}
