package com.android.core.control;

import android.content.Context;
import android.widget.ImageView;

import com.android.core.R;
import com.android.core.widget.GlideCircleTransform;
import com.bumptech.glide.Glide;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-14 16:02
 * @GitHub: https://github.com/meikoz
 */
public class GlieTip {

    public static GlieTip instance = new GlieTip();

    public GlieTip() {
    }

    public static GlieTip getInstance() {
        return instance;
    }

    // 加载网络图片
    public void load(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.color.abc_tab_text_normal)
                .error(R.color.abc_tab_text_normal)
                .crossFade()
                .into(imageView);
    }

    // 加载圆型网络图片
    public void loadCircle(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.color.abc_tab_text_normal)
                .error(R.color.abc_tab_text_normal)
                .transform(new GlideCircleTransform(context))
                .crossFade()
                .into(imageView);
    }

    // 加载drawable图片
    public void load(Context context, int resid, ImageView imageView) {
        Glide.with(context)
                .load(resid)
                .placeholder(R.color.abc_tab_text_normal)
                .error(R.color.abc_tab_text_normal)
                .crossFade()
                .into(imageView);
    }

}
