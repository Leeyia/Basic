package com.racofix.basic.image;

import android.widget.ImageView;

public class ImageConfigure {

    private final ImageConfigure.configure configure;
    private String stringUrl;
    private ImageEngine engine;

    public ImageConfigure(ImageConfigure.configure configure) {
        this.configure = configure;
        this.engine = configure.engine;
        this.stringUrl = configure.stringUrl;
    }

    public String getStringUrl() {
        return stringUrl;
    }

    public ImageEngine getEngine() {
        return engine;
    }

    public void into(ImageView target) {
        this.getEngine().load(this, target);
    }

    public static class configure {

        private String stringUrl;
        private ImageEngine engine = new PicassoEngine();

        configure() {

        }

        public ImageConfigure.configure url(String url) {
            this.stringUrl = url;
            return this;
        }

        public ImageConfigure.configure imageEngine(ImageEngine imageEngine) {
            this.engine = imageEngine;
            return this;
        }

        public ImageConfigure build() {
            return new ImageConfigure(this);
        }
    }
}
