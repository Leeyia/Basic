package com.meikoz.basic.model;

import android.text.TextUtils;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.meikoz.core.manage.imageloader.LoaderConfig;
import com.meikoz.core.manage.imageloader.Strategy;

import java.io.Serializable;

/**
 * @USER: zhoujinlong
 * @DATE: 2017/4/27
 */

public class ImageLoaderStrategy implements Strategy {

    @Override
    public void load(int mode, LoaderConfig mConfig) {
        if (mConfig.targetV == null)
            throw new NullPointerException("ImageView is null!");

        DrawableRequestBuilder<? extends Serializable> manager = Glide.with(mConfig.targetV.getContext())
                .load(TextUtils.isEmpty(mConfig.stringUrl) ? mConfig.resourceUrl : mConfig.stringUrl);

        switch (mode) {
            case LoaderConfig.LOADER_IMAGE_DEFAULT:
                manager.placeholder(mConfig.placeHoldId).error(mConfig.errorId);
                break;

            case LoaderConfig.LOADER_IMAGE_TRANSFORM:
                if (mConfig.transform == null)
                    throw new NullPointerException("transform is null!");

                manager.transform(mConfig.transform);
                break;

            case LoaderConfig.LOADER_IMAGE_ANIMATE:
                if (mConfig.animateId == 0 && mConfig.animator == null)
                    throw new NullPointerException("animate is null!");
                if (mConfig.animateId != 0)
                    manager.animate(mConfig.animateId);
                else
                    manager.animate(mConfig.animator);
                break;
        }
    }
}
