package com.dintech.api.samples.apis;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {
    @GET("user/info")
    Call<Response> user();
}
