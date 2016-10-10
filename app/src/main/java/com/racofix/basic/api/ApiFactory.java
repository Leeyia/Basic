package com.racofix.basic.api;

import com.android.core.BuildConfig;
import com.android.core.api.RestApi;

import java.util.HashMap;
import java.util.Map;


/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:15
 * @GitHub: https://github.com/meikoz
 */
public class ApiFactory {

    public static ApiServiceI createApi() {
        return provideService(ApiServiceI.class);
    }

    private static Map<Class, Object> m_service = new HashMap();

    private static <T> T provideService(Class cls) {
        Object serv = m_service.get(cls);
        if (serv == null) {
            synchronized (cls) {
                serv = m_service.get(cls);
                if (serv == null) {
                    serv = RestApi.getIns().createService(BuildConfig.DEBUG, cls);
                    m_service.put(cls, serv);
                }
            }
        }
        return (T) serv;
    }
}
