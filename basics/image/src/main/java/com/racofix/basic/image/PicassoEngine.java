package com.racofix.basic.image;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoEngine implements ImageEngine {

    @Override
    public void load(ImageConfigure configure, ImageView imageView) {
        Picasso.get().load(configure.getStringUrl()).into(imageView);
    }
}
