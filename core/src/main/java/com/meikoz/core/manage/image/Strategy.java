package com.meikoz.core.manage.image;

/**
 * @USER: zhoujinlong
 * @DATE: 2017/4/27
 */

public interface Strategy {
    void load(LoaderConfig config);

    void transform(LoaderConfig config);

    void animate(LoaderConfig config);
}
