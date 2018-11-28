package com.racofix.basic.image;

import com.squareup.picasso.Picasso;

public class ImageConfigureImpl implements ImageConfigure {

    private final Picasso picasso;

    public ImageConfigureImpl(){
        this.picasso = Picasso.get();
    }

    @Override
    public void error() {
    }
}
