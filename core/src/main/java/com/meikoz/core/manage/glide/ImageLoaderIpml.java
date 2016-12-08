package com.meikoz.core.manage.glide;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @User: 蜡笔小新
 * @date: 16-12-7
 * @GitHub: https://github.com/meikoz
 */

public class ImageLoaderIpml implements ImageLoader {

    @Override
    public void loadNet(ImageView target, String url) {
        Glide.with(target.getContext()).load(url).into(target);
    }
}
