package com.racofix.api;

import com.android.core.api.HttpClient;

import java.util.HashMap;
import java.util.Map;


/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:15
 * @GitHub: https://github.com/meikoz
 */
public class Factory {

    public static BaseHttpService provideHttpService() {
        return provideService(BaseHttpService.class);
    }

    private static Map<Class, Object> m_service = new HashMap();

    private static <T> T provideService(Class cls) {
        Object serv = m_service.get(cls);
        if (serv == null) {
            synchronized (cls) {
                serv = m_service.get(cls);
                if (serv == null) {
                    serv = HttpClient.getIns(ApiConstant.BASE_URL).createService(cls);
                    m_service.put(cls, serv);
                }
            }
        }
        return (T) serv;
    }
}
