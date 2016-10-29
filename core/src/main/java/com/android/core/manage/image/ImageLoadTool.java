package com.android.core.manage.image;

import android.content.Context;

public class ImageLoadTool implements ImageLoadToolI {

    static ImageLoadTool mProxy;
    final GlideLoaderStrategy mStrategy;

    ImageLoadTool() {
        mStrategy = new GlideLoaderStrategy();
    }

    // 单例获取唯一对象
    public static ImageLoadTool getInstance() {
        if (mProxy == null) {
            synchronized (ImageLoadTool.class) {
                mProxy = new ImageLoadTool();
            }
        }
        return mProxy;
    }

    @Override
    public void load(Context context, ImageLoader var1) {
        mStrategy.load(context, var1);
    }

    @Override
    public void animate(Context context, ImageLoader var2) {
        mStrategy.animate(context,var2);
    }

    @Override
    public void transform(Context context, ImageLoader var3) {
        mStrategy.transform(context,var3);
    }

}
