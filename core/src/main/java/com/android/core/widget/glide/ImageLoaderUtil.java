package com.android.core.widget.glide;

import android.content.Context;

/**
 * see: https://github.com/CameloeAnthony/MVPCommon
 * use this class to load image,single instance
 */
public class ImageLoaderUtil {

    public static final int PIC_LARGE = 0;
    public static final int PIC_MEDIUM = 1;
    public static final int PIC_SMALL = 2;

    public static final int LOAD_STRATEGY_NORMAL = 0;
    public static final int LOAD_STRATEGY_ONLY_WIFI = 1;

    private static ImageLoaderUtil mInstance;
    private BaseImageLoaderStrategy mStrategy;

    public ImageLoaderUtil() {
        mStrategy = new GlideImageLoaderStrategy();
    }

    public static ImageLoaderUtil getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderUtil.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderUtil();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }


    public void loadImage(Context context, ImageLoader img) {
        mStrategy.loadImage(context, img);
    }

    public void loadCircleImage(Context context, ImageLoader img) {
        mStrategy.loadCircleImage(context, img);
    }

    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        mStrategy = strategy;
    }
}
