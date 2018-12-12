package com.racofix.basic.image;

import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class ImageConfigure {

    private final String imageUrl;
    private final ImageView target;
    private final Context context;

    private ImageConfigure(Configure configure) {
        this.imageUrl = configure.mImageUrl;
        this.target = configure.mTarget;
        this.context = configure.wrf.get();
    }

    public Context getContext() {
        return this.context;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public ImageView getTarget() {
        return this.target;
    }

    public static class Configure {
        private String mImageUrl;
        private ImageView mTarget;
        private WeakReference<Context> wrf;

        public Configure() {
        }

        public Configure imageUrl(String url) {
            this.mImageUrl = url;
            return this;
        }

        public Configure into(ImageView target) {
            this.mTarget = target;
            this.wrf = new WeakReference<>(mTarget.getContext());
            return this;
        }

        public ImageConfigure build() {
            return new ImageConfigure(this);
        }
    }
}
