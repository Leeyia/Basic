package com.meikoz.core.manage.glide;

/**
 * @User: 蜡笔小新
 * @date: 16-12-7
 * @GitHub: https://github.com/meikoz
 */

public class ImageFactory {

    private static ImageLoader loader;

    public static ImageLoader getInstance() {
        if (loader == null) {
            synchronized (ImageFactory.class) {
                if (loader == null) {
                    loader = new ImageLoaderIpml();
                }
            }
        }
        return loader;
    }
}
