package com.racofix.basic.http.model;

import com.racofix.basic.http.RealCallback;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 装饰器模式调用Call
 *
 * @param <T> 数据类型
 */
public class RealCall<T> {

    private final Call<T> mRawCall;

    public RealCall(Call<T> rawCall) {
        this.mRawCall = rawCall;
    }

    public Response<T> execute() throws IOException {
        Response<T> response = this.mRawCall.execute();
        T body = response.body();
        if (body instanceof DataBody) {
            ((DataBody<T>) body).setRawResponse(response.raw());
        }
        return response;
    }

    public void enqueue(final RealCallback<T> callback) {
        this.mRawCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                T body = response.body();
                if (body instanceof DataBody) {
                    DataBody<T> body1 = (DataBody<T>) body;
                }
                callback.successful(body);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.failure(t.getMessage());
            }
        });
    }

    public boolean isExecuted() {
        return this.mRawCall.isExecuted();
    }

    public void cancel() {
        this.mRawCall.cancel();
    }

    public boolean isCanceled() {
        return this.mRawCall.isCanceled();
    }

    public RealCall<T> clone() {
        return new RealCall<>(mRawCall.clone());
    }

    public Request request() {
        return this.mRawCall.request();
    }
}
