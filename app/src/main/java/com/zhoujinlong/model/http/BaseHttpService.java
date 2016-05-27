package com.zhoujinlong.model.http;


import com.zhoujinlong.model.bean.Classify;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public interface BaseHttpService {


    @Headers("Cache-Control: public, max-age=3600")
    @GET("tnfs/api/list")
    Call<Classify> getImageClassify(@Query("id") int id);


}
