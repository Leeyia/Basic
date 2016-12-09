package com.meikoz.basic.app;

import com.meikoz.core.manage.interfacee.ImageLoadInterface;

/**
 * @User: 蜡笔小新
 * @date: 16-12-7
 * @GitHub: https://github.com/meikoz
 */

public class ImageControl {

    private static ImageLoadInterface mImageLoadInterface;

    public static ImageLoadInterface getInstance() {
        if (mImageLoadInterface == null) {
            synchronized (ImageControl.class) {
                if (mImageLoadInterface == null) {
                    mImageLoadInterface = new ImageLoadInterfaceImpl();
                }
            }
        }
        return mImageLoadInterface;
    }
}
