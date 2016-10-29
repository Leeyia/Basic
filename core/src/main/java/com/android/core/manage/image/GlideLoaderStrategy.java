package com.android.core.manage.image;

import android.content.Context;
import android.text.TextUtils;

import com.bumptech.glide.Glide;

public class GlideLoaderStrategy {

    // 加载图片方法
    public void load(Context context, ImageLoader var1) {
        Glide.with(context)
                .load(TextUtils.isEmpty(var1.getUrl()) ? var1.getUrlRes() : var1.getUrl()) //可以加载本地资源
                .placeholder(var1.getPlaceHolder())
                .error(var1.getError())
                .transform(var1.getTransformation())
                .into(var1.getImgView());
    }

    public void animate(Context context, ImageLoader var2) {
        Glide.with(context)
                .load(TextUtils.isEmpty(var2.getUrl()) ? var2.getUrlRes() : var2.getUrl()) //可以加载本地资源
                .placeholder(var2.getPlaceHolder())
                .error(var2.getError())
                .animate(var2.getAnimationId())
                .into(var2.getImgView());
    }

    public void transform(Context context, ImageLoader var3) {
        Glide.with(context)
                .load(TextUtils.isEmpty(var3.getUrl()) ? var3.getUrlRes() : var3.getUrl()) //可以加载本地资源
                .placeholder(var3.getPlaceHolder())
                .error(var3.getError())
                .transform(var3.getTransformation())
                .into(var3.getImgView());
    }
}
