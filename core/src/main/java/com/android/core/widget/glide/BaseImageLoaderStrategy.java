package com.android.core.widget.glide;

import android.content.Context;

/**
 * Created by Anthony on 2016/3/3.
 * Class Note:
 * abstract class/interface defined to load image
 * (Strategy Pattern used here)
 */
public interface BaseImageLoaderStrategy {
   void loadImage(Context ctx, ImageLoader img);
   void loadCircleImage(Context ctx, ImageLoader img);
}
