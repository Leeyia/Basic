package com.zhoujinlong.model.http;

import com.android.core.model.http.HttpClient;
import com.zhoujinlong.model.config.ApiConstant;

import java.util.HashMap;
import java.util.Map;


/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:15
 * @GitHub: https://github.com/meikoz
 */
public class Factory {

    public static BaseUserService provideUserService() {
        return provideService(BaseUserService.class);
    }

    public static BaseHttpService provideHttpService() {
        return provideService(BaseHttpService.class);
    }

    private static Map<Class, Object> m_service = new HashMap();

    public static <T> T provideService(Class cls) {
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
