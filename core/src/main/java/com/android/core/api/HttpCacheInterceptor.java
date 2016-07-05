package com.android.core.api;

import android.util.Log;

import com.android.core.MainApp;
import com.android.core.control.NetWorkHelper;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:15
 * @GitHub: https://github.com/meikoz
 */
public class HttpCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetWorkHelper.isNetConnected(MainApp.getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.d("com.android.core", "no network");
        }

        Response originalResponse = chain.proceed(request);
        if (NetWorkHelper.isNetConnected(MainApp.getContext())) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
