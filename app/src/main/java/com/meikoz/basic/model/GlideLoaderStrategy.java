package com.meikoz.basic.model;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.meikoz.core.manage.image.ImageLoaderManager;
import com.meikoz.core.manage.image.LoaderConfig;
import com.meikoz.core.manage.image.Strategy;

/**
 * @USER: zhoujinlong
 * @DATE: 2017/4/27
 */

public class GlideLoaderStrategy implements Strategy {

    public static void displayImage(String url, ImageView targetV) {
        LoaderConfig config = new LoaderConfig.Builder()
                .load(url)
                .into(targetV)
                .build();
        ImageLoaderManager.getInstance(new GlideLoaderStrategy())
                .display(LoaderConfig.LOADER_IMAGE_NOERROR, config);
    }

    public static void displayImage(String url, ImageView targetV, int placeHolderId) {
        LoaderConfig config = new LoaderConfig.Builder()
                .load(url)
                .placeHolder(placeHolderId)
                .error(placeHolderId)
                .into(targetV)
                .build();
        ImageLoaderManager.getInstance(new GlideLoaderStrategy())
                .display(LoaderConfig.LOADER_IMAGE_NOERROR, config);
    }

    public static void displayImage(String url, ImageView targetV, int placeHolderId, BitmapTransformation transform) {
        LoaderConfig config = new LoaderConfig.Builder()
                .load(url)
                .placeHolder(placeHolderId)
                .error(placeHolderId)
                .transform(transform)
                .into(targetV)
                .build();
        ImageLoaderManager.getInstance(new GlideLoaderStrategy())
                .display(LoaderConfig.LOADER_IMAGE_TRANSFORM, config);
    }

    public static void displayImage(String url, ImageView targetV, int placeHolderId, int animateId) {
        LoaderConfig config = new LoaderConfig.Builder()
                .load(url)
                .placeHolder(placeHolderId)
                .error(placeHolderId)
                .animateId(animateId)
                .into(targetV)
                .build();
        ImageLoaderManager.getInstance(new GlideLoaderStrategy())
                .display(LoaderConfig.LOADER_IMAGE_ANIMATE, config);
    }

    @Override
    public void load(LoaderConfig config) {
        configurationBuilder(config).into(config.targetV);
    }

    @Override
    public void transform(LoaderConfig config) {
        configurationBuilder(config).transform(config.transform).into(config.targetV);
    }

    @Override
    public void animate(LoaderConfig config) {
        if (config.animateId != 0)
            configurationBuilder(config).animate(config.animateId).into(config.targetV);
        else
            configurationBuilder(config).animate(config.animator).into(config.targetV);
    }

    private DrawableRequestBuilder configurationBuilder(LoaderConfig config) {
        return Glide
                .with(config.targetV.getContext())
                .load(TextUtils.isEmpty(config.stringUrl) ? config.resourceUrl : config.stringUrl)
                .placeholder(config.placeHoldId)
                .error(config.errorId);
    }
}
