package com.meikoz.core.manage.imageloader;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.meikoz.core.R;

/**
 * @USER: zhoujinlong
 * @DATE: 2017/4/27
 */

public class ImageLoaderConfig {

    public static final int STRING_URL = 0;

    private String stringUrl;
    private Integer resourceUrl;
    private ImageView targetV;
    private BitmapTransformation transform;
    private Integer placeHoldId;
    private int errorId;

    private ImageLoaderConfig() {

    }

    private ImageLoaderConfig(Builder builder) {
        this.stringUrl = builder.stringUrl;
        this.resourceUrl = builder.resourceUrl;
        this.targetV = builder.targetV;
        this.transform = builder.transform;
        this.placeHoldId = builder.placeHoldId;
        this.errorId = builder.errorId;
    }

    public String getStringUrl() {
        return stringUrl;
    }

    public Integer getResourceUrl() {
        return resourceUrl;
    }

    public ImageView getTargetV() {
        return targetV;
    }

    public BitmapTransformation getTransform() {
        return transform;
    }

    public Integer getPlaceHoldId() {
        return placeHoldId;
    }

    public int getErrorId() {
        return errorId;
    }

    public static class Builder {
        private String stringUrl;
        private Integer resourceUrl;
        private ImageView targetV;
        private BitmapTransformation transform;
        private Integer placeHoldId;
        private int errorId;

        public Builder() {
            this.stringUrl = "";
            this.resourceUrl = R.color.colorPlaceHolder;
            this.placeHoldId = R.color.colorPlaceHolder;
            this.errorId = R.color.colorPlaceHolder;
            this.targetV = null;
            this.transform = null;
        }

        public Builder load(String stringUrl) {
            this.stringUrl = stringUrl;
            return this;
        }

        public Builder load(int urlRes) {
            this.resourceUrl = urlRes;
            return this;
        }

        public Builder into(ImageView imgView) {
            this.targetV = imgView;
            return this;
        }

        public Builder transform(BitmapTransformation mTransformation) {
            this.transform = mTransformation;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHoldId = placeHolder;
            return this;
        }

        public Builder error(int error) {
            this.errorId = error;
            return this;
        }

        public ImageLoaderConfig build() {
            return new ImageLoaderConfig(this);
        }
    }
}
