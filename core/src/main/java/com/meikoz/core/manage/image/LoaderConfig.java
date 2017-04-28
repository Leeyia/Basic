package com.meikoz.core.manage.image;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.meikoz.core.R;

/**
 * @USER: zhoujinlong
 * @DATE: 2017/4/27
 */

public class LoaderConfig {

    public static final int LOADER_IMAGE_DEFAULT = 0;
    public static final int LOADER_IMAGE_TRANSFORM = 1;
    public static final int LOADER_IMAGE_ANIMATE = 2;
    public static final int LOADER_IMAGE_NOERROR = 3;

    public String stringUrl;
    public Integer resourceUrl;
    public ImageView targetV;
    public BitmapTransformation transform;
    public Integer placeHoldId;
    public Integer errorId;
    public Integer animateId;
    public ViewPropertyAnimation.Animator animator;

    private LoaderConfig() {

    }

    private LoaderConfig(Builder builder) {
        this.stringUrl = builder.stringUrl;
        this.resourceUrl = builder.resourceUrl;
        this.targetV = builder.targetV;
        this.transform = builder.transform;
        this.placeHoldId = builder.placeHoldId;
        this.errorId = builder.errorId;
        this.animateId = builder.animateId;
        this.animator = builder.animator;
    }

    public static class Builder {
        private String stringUrl;
        private Integer resourceUrl;
        private ImageView targetV;
        private BitmapTransformation transform;
        private Integer placeHoldId;
        private Integer errorId;
        private Integer animateId;
        private ViewPropertyAnimation.Animator animator;

        public Builder() {
            this.stringUrl = "";
            this.resourceUrl = R.color.colorPlaceHolder;
            this.placeHoldId = R.color.colorPlaceHolder;
            this.errorId = R.color.colorPlaceHolder;
            this.animateId = 0;
            this.animator = animator;
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

        public Builder placeHolder(Integer placeHolder) {
            this.placeHoldId = placeHolder;
            return this;
        }

        public Builder error(Integer error) {
            this.errorId = error;
            return this;
        }

        public Builder animateId(Integer errorId) {
            this.errorId = errorId;
            return this;
        }

        public Builder animator(ViewPropertyAnimation.Animator animator) {
            this.animator = animator;
            return this;
        }

        public LoaderConfig build() {
            return new LoaderConfig(this);
        }
    }
}
