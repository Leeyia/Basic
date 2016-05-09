package com.common.model.control;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.common.R;
import com.common.view.widget.GlideCircleTransform;

/**
 * author meikoz on 2016/4/13.
 * email  meikoz@126.com
 */
public class GlideProxy {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";
    private static GlideProxy instance;
    private GlideProxy() {}

    public static GlideProxy getInstance() {
        if (instance ==null){
            instance = new GlideProxy();
        }
        return instance;
    }

    // 将资源ID转为Uri
    public Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }


    // 加载drawable图片
    public void loadResImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .placeholder(R.color.material_white70)
                .error(R.color.material_white70)
                .crossFade()
                .into(imageView);
    }

    // 加载本地图片
    public void loadLocalImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load("file://" + path)
                .placeholder(R.color.material_white70)
                .error(R.color.material_white70)
                .crossFade()
                .into(imageView);
    }

    // 加载网络圆型图片
    public void loadCircleImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.color.material_white70)
                .error(R.color.material_white70)
                .crossFade()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    // 加载drawable圆型图片
    public void loadCircleResImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .placeholder(R.color.material_white70)
                .error(R.color.material_white70)
                .crossFade()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    // 加载本地圆型图片
    public void loadCircleLocalImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load("file://" + path)
                .placeholder(R.color.material_white70)
                .error(R.color.material_white70)
                .crossFade()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

}
