package com.meikoz.basic.model;

import com.meikoz.core.manage.imageloader.ImageLoaderConfig;
import com.meikoz.core.manage.imageloader.Strategy;

/**
 * @USER: zhoujinlong
 * @DATE: 2017/4/27
 */

public class ImageLoaderStrategy implements Strategy {

    @Override
    public void load(int mLoadMode, ImageLoaderConfig mConfig) {
        /*if (mConfig.getTargetV() == null)
            throw new Exception("ImageView Not null!");*/
        switch (mLoadMode){
            case ImageLoaderConfig.STRING_URL:
                break;
        }
    }
}
