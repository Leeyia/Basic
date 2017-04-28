package com.meikoz.core.manage.imageloader;

/**
 * @USER: zhoujinlong
 * @DATE: 2017/4/27
 */
public class ImageControl implements Strategy {

    private static ImageControl mImageControl = null;
    private final Strategy mStrategy;

    public static ImageControl getInstance(Strategy strategy) {
        if (mImageControl == null) {
            synchronized (ImageControl.class) {
                if (mImageControl == null)
                    mImageControl = new ImageControl(strategy);
            }
        }
        return mImageControl;
    }

    private ImageControl(Strategy strategy) {
        this.mStrategy = strategy;
    }

    @Override
    public void load(int loadMode, LoaderConfig mConfig) {
        mStrategy.load(loadMode, mConfig);
    }
}
