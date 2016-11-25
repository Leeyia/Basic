package com.meikoz.basic.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.meikoz.basic.R;
import com.meikoz.basic.bean.Gank;
import com.meikoz.core.adapter.RecyclerAdapter;
import com.meikoz.core.adapter.RecyclerViewHolder;

import java.util.List;

/**
 * @User: 蜡笔小新
 * @date: 16-11-25
 * @GitHub: https://github.com/meikoz
 */

public class NetworkAdapter extends RecyclerAdapter<Gank.ResultsBean> {

    public NetworkAdapter(Context context, int layoutId, List<Gank.ResultsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Gank.ResultsBean item) {
        Glide.with(mContext).load(item.getUrl())
                .into((ImageView) holder.getView(R.id.iv_home_img));
    }
}
