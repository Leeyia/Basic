package com.meikoz.basic.app;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.meikoz.core.manage.interfacee.ImageLoadInterface;

/**
 * @User: 蜡笔小新
 * @date: 16-12-7
 * @GitHub: https://github.com/meikoz
 */

public class ImageLoadInterfaceImpl implements ImageLoadInterface {

    @Override
    public void loadNet(ImageView target, String url) {
        this.load(target, url, -1);
    }

    @Override
    public void loadNet(ImageView target, String url, int animaRes) {
        this.load(target, url, animaRes);
    }

    @Override
    public void loadDrawable(ImageView target, int resId) {
        this.load(target, resId, -1);
    }

    @Override
    public void loadDrawable(ImageView target, int resId, int animal) {
        this.load(target, resId, animal);
    }

    private void load(ImageView target, String url, int animaRes) {
        if (animaRes == -1)
            Glide.with(target.getContext()).load(url).centerCrop().into(target);
        else
            Glide.with(target.getContext()).load(url).animate(animaRes).centerCrop().into(target);
    }


    private void load(ImageView target, int resId, int animaRes) {
        if (animaRes == -1)
            Glide.with(target.getContext()).load(resId).centerCrop().into(target);
        else
            Glide.with(target.getContext()).load(resId).animate(animaRes).centerCrop().into(target);
    }
}
