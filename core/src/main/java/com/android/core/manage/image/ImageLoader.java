package com.android.core.manage.image;

import android.widget.ImageView;

import com.android.core.R;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class ImageLoader {

    private String url;
    private int urlRes;
    private ImageView imgView;
    private BitmapTransformation mTransformation;
    private int placeHolder;
    private int error;
    private int animationId;

    public String getUrl() {
        return url;
    }

    public int getUrlRes() {
        return urlRes;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public BitmapTransformation getTransformation() {
        return mTransformation;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public int getError() {
        return error;
    }

    public int getAnimationId() {
        return animationId;
    }

    private ImageLoader(Builder builder) {
        this.url = builder.url;
        this.urlRes = builder.urlRes;
        this.placeHolder = builder.placeHolder;
        this.error = builder.error;
        this.imgView = builder.imgView;
        this.mTransformation = builder.mTransformation;
        this.animationId = builder.animationId;
    }

    public static class Builder {
        private String url;
        private int urlRes;
        private ImageView imgView;
        private BitmapTransformation mTransformation;
        private int placeHolder;
        private int error;
        private int animationId;

        public Builder() {
            this.url = "";
            this.placeHolder = R.color.colorPlaceHolder;
            this.error = R.color.colorPlaceHolder;
            this.imgView = null;
            this.mTransformation = null;
            this.animationId = 0;
        }

        public Builder load(String url) {
            this.url = url;
            return this;
        }

        public Builder load(int urlRes) {
            this.urlRes = urlRes;
            return this;
        }

        public Builder into(ImageView imgView) {
            this.imgView = imgView;
            return this;
        }

        public Builder transform(BitmapTransformation mTransformation) {
            this.mTransformation = mTransformation;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder error(int error) {
            this.error = error;
            return this;
        }

        public Builder animate(int animationId) {
            this.animationId = animationId;
            return this;
        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }
    }
}
