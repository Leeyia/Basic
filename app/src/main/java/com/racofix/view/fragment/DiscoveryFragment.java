package com.racofix.view.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.racofix.R;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class DiscoveryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.activity_login, null);
    }
    //    @Override
//    protected int getLayoutResource() {
//        return R.layout.activity_login;
//    }
//
//    @Override
//    protected void onInitView() {
//
//    }

}
