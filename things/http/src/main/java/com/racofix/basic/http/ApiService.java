package com.racofix.basic.http;


import com.racofix.basic.http.model.RealCall;

import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @GET("toutiao/index?type=shehui&key=85a15c14d09f886463dcd52d11701537")
    @Headers("Content-Type:application/json;charset=utf-8")
    RealCall<CityInfo> getCityCall();
}
