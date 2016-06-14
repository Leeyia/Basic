package com.zhoujinlong.model.http;


import com.google.gson.JsonElement;
import com.zhoujinlong.model.bean.Classify;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:15
 * @GitHub: https://github.com/meikoz
 */
public interface BaseHttpService {

    @GET("tnfs/api/list")
    Call<Classify> getImageClassify(@Query("id") int id);


}
