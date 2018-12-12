package com.racofix.basic.image;

public class ImageLoader {

    private ImageEngine imageEngine;
    private static ImageLoader instance;

    public static ImageLoader getDefalut() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) ImageLoader.instance = new ImageLoader();
            }
        }
        return ImageLoader.instance;
    }

    private ImageLoader() {
        this.engine(new PicassoImageEngine());
    }

    public ImageLoader engine(ImageEngine engine) {
        this.imageEngine = engine;
        return this;
    }

    public void display(ImageConfigure configure) {
        this.imageEngine.display(configure);
    }
}
