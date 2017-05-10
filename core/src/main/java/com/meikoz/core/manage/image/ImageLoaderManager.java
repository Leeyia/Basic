package com.meikoz.core.manage.image;

/**
 * @USER: zhoujinlong
 * @DATE: 2017/4/27
 */
public class ImageLoaderManager {

    private static ImageLoaderManager mImageControl = null;
    private final Strategy mStrategy;

    public static ImageLoaderManager getInstance(Strategy strategy) {
        if (mImageControl == null) {
            synchronized (ImageLoaderManager.class) {
                if (mImageControl == null)
                    mImageControl = new ImageLoaderManager(strategy);
            }
        }
        return mImageControl;
    }

    private ImageLoaderManager(Strategy strategy) {
        this.mStrategy = strategy;
    }

    public void display(int mode, LoaderConfig mConfig) {
        if (mConfig.targetV == null)
            throw new NullPointerException("ImageView is null!");

        switch (mode) {
            case LoaderConfig.LOADER_IMAGE_DEFAULT:
                mStrategy.load(mConfig);
                break;

            case LoaderConfig.LOADER_IMAGE_TRANSFORM:
                if (mConfig.transform == null)
                    throw new NullPointerException("transform is null!");
                mStrategy.transform(mConfig);
                break;

            case LoaderConfig.LOADER_IMAGE_ANIMATE:
                if (mConfig.animateId == 0 && mConfig.animator == null)
                    throw new NullPointerException("animate is null!");
                mStrategy.animate(mConfig);
                break;
        }
    }
}
