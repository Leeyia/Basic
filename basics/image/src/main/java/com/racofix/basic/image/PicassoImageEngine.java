package com.racofix.basic.image;

import com.squareup.picasso.Picasso;

public class PicassoImageEngine implements ImageEngine {

    @Override
    public void display(ImageConfigure configure) {
        Picasso.get().load(configure.getImageUrl()).into(configure.getTarget());
    }
}
