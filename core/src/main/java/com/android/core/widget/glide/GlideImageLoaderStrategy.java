package com.android.core.widget.glide;

import android.content.Context;

import com.android.core.control.NetWorkHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: 蜡笔小新
 * @date: 2016-08-01 16:42
 * @GitHub: https://github.com/meikoz
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {
    @Override
    public void loadImage(Context ctx, ImageLoader img) {
        boolean flag = NetWorkHelper.isWifiConnected(ctx);
        //如果不是在wifi下加载图片，直接加载
        if (!flag) {
            loadNormal(ctx, img);
            return;
        }

        int strategy = img.getWifiStrategy();
        if (strategy == ImageLoaderUtil.LOAD_STRATEGY_ONLY_WIFI) {
            int netType = NetWorkHelper.getNetWorkType(ctx);
            //如果是在wifi下才加载图片，并且当前网络是wifi,直接加载
            if (netType == NetWorkHelper.NETWORKTYPE_WIFI) {
                loadNormal(ctx, img);
            } else {
                //如果是在wifi下才加载图片，并且当前网络不是wifi，加载缓存
                loadCache(ctx, img);
            }
        } else {
            //如果不是在wifi下才加载图片
            loadNormal(ctx, img);
        }
    }

    /**
     * load image with Glide
     */
    private void loadNormal(Context ctx, ImageLoader img) {
        Glide.with(ctx).load(img.getUrl()).placeholder(img.getPlaceHolder()).into(img.getImgView());
    }

    /**
     * load cache image with Glide
     */
    private void loadCache(Context ctx, ImageLoader img) {
        Glide.with(ctx).using(new StreamModelLoader<String>() {
            @Override
            public DataFetcher<InputStream> getResourceFetcher(final String model, int i, int i1) {
                return new DataFetcher<InputStream>() {
                    @Override
                    public InputStream loadData(Priority priority) throws Exception {
                        throw new IOException();
                    }

                    @Override
                    public void cleanup() {

                    }

                    @Override
                    public String getId() {
                        return model;
                    }

                    @Override
                    public void cancel() {

                    }
                };
            }
        }).load(img.getUrl()).placeholder(img.getPlaceHolder()).diskCacheStrategy(DiskCacheStrategy.ALL).into(img.getImgView());
    }

    public void loadCircleImage(Context ctx, ImageLoader img) {
        Glide.with(ctx).load(img.getUrl()).placeholder(img.getPlaceHolder()).transform(new GlideCircleTransform(ctx)).into(img.getImgView());
    }
}
