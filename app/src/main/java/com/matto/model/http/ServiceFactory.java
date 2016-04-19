package com.matto.model.http;

import com.common.model.http.HttpClient;
import com.matto.model.config.ApiConstant;
import com.matto.pojo.Gank;

import retrofit2.Call;


/**
 * author miekoz on 2016/3/21.
 * email  meikoz@126.com
 */
public class ServiceFactory {

    private static MainService mService;

    public static MainService getMainIns(){
        if (mService == null){
            mService = HttpClient.getIns(ApiConstant.BASE_URL).createService(MainService.class);
        }
        return mService;
    }

}
