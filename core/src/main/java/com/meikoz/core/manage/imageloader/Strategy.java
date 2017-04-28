package com.meikoz.core.manage.imageloader;

/**
 * @USER: zhoujinlong
 * @DATE: 2017/4/27
 */

public interface Strategy {
    void load(int loadMode, LoaderConfig mConfig);
}
