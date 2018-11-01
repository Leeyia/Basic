package com.racofix.develop.http.model;

public class DataBody<T> {

    private final T mBody;
    private final int mErrorCode;
    private final String mErrorMessage;
    private final String mErrorUrl;
    private final long mPolicyExpireMs;
    private final long mNextRequestSleepMs;
    private okhttp3.Response mRawResponse;
    private boolean mIsFromCache;

    public DataBody(T body, int errorCode, String errorMessage, String errorUrl, long policyExpireMs,
                    long nextRequestSleepMs) {
        mBody = body;
        mErrorCode = errorCode;
        mErrorMessage = errorMessage;
        mErrorUrl = errorUrl;
        mPolicyExpireMs = policyExpireMs;
        mNextRequestSleepMs = nextRequestSleepMs;
    }

    void setRawResponse(okhttp3.Response rawResponse) {
        mRawResponse = rawResponse;
    }

    public T body() {
        return mBody;
    }

    public okhttp3.Response raw() {
        return mRawResponse;
    }

    public int errorCode() {
        return mErrorCode;
    }

    public String errorMessage() {
        return mErrorMessage;
    }

    public String errorUrl() {
        return mErrorUrl;
    }

    public long policyExpireMs() {
        return mPolicyExpireMs;
    }

    public long nextRequestSleepMs() {
        return mNextRequestSleepMs;
    }

    public boolean isFromCache() {
        return mIsFromCache;
    }

    public void setIsFromCache(boolean isFromCache) {
        mIsFromCache = isFromCache;
    }
}
