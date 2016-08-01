package com.racofix.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;

import com.android.core.adapter.RecyclerAdapter;
import com.android.core.adapter.RecyclerViewHolder;
import com.bumptech.glide.Glide;
import com.racofix.R;
import com.racofix.model.repo.Classify;
import com.racofix.view.activity.SwipBackActivity;

import java.util.List;

/**
 * @author: 蜡笔小新
 * @date: 2016-05-31 14:15
 * @GitHub: https://github.com/meikoz
 */
public class HomeRecyclerAdapter extends RecyclerAdapter<Classify.TngouEntity> {

    private String URL_Base = "http://tnfs.tngou.net/image";

    public HomeRecyclerAdapter(Context context, int layoutId, List<Classify.TngouEntity> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(final RecyclerViewHolder holder, final Classify.TngouEntity item) {
        Uri uri = Uri.parse(URL_Base + item.getImg());
        Glide.with(mContext).load(uri).into((ImageView) holder.getView(R.id.sv_classitfy_img));
        holder.setText(R.id.sv_classitfy_des, item.getTitle());
        holder.setOnClickListener(R.id.sv_classitfy_img, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(holder.getView(R.id.sv_classitfy_img), item.getTitle(), Snackbar.LENGTH_LONG).show();
                SwipBackActivity.start(mContext);
            }
        });
    }
}
