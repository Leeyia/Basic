package com.dintech.api.http;

import retrofit2.Call;
import retrofit2.Response;

public abstract class TCallback<T> implements retrofit2.Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        this.done(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        call.cancel();
        this.fail(t.getMessage());
    }

    public abstract void done(T body);

    public abstract void fail(String message);
}
