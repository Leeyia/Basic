package com.matto.adapter;

import android.content.Context;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.matto.model.bean.Gank;

import java.util.List;

/**
 * author meikoz on 2016/4/26.
 * email  meikoz@126.com
 */
public class CommonAdapter extends QuickAdapter<Gank> {

    public CommonAdapter(Context context, int layoutResId, List<Gank> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, Gank item) {

    }
}
