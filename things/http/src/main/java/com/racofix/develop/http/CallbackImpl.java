package com.racofix.develop.http;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackImpl<T> implements Callback<DataContainer<T>> {

    @Override
    public void onResponse(Call<DataContainer<T>> call, Response<DataContainer<T>> response) {

    }

    @Override
    public void onFailure(Call<DataContainer<T>> call, Throwable t) {

    }
}
