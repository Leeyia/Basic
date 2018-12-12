package com.racofix.basic.image;

import com.bumptech.glide.Glide;

public class GlideImageEngine implements ImageEngine {
    @Override
    public void display(ImageConfigure configure) {
        Glide.with(configure.getContext()).load(configure.getImageUrl()).into(configure.getTarget());
    }
}
