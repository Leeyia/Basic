package com.meikoz.basic.api;

import com.meikoz.core.api.RestApi;
import com.meikoz.core.ui.TabStripView;

/**
 * @User: 蜡笔小新
 * @date: 16-11-15
 * @GitHub: https://github.com/meikoz
 */

public class ApiManager {
    public static APIService createApi() {
        return RestApi.getInstance().create(APIService.class);
    }
}
