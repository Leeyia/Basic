package com.racofix.view.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;

import com.android.core.adapter.RecyclerAdapter;
import com.android.core.adapter.RecyclerViewHolder;
import com.android.core.widget.glide.ImageLoader;
import com.android.core.widget.glide.ImageLoaderUtil;
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
//        Uri uri = Uri.parse(URL_Base + item.getImg());
        String url = URL_Base + item.getImg();
        ImageView view = holder.getView(R.id.sv_classitfy_img);
        ImageLoaderUtil.getInstance().loadCircleImage(mContext, new ImageLoader.Builder()
                .url(url)
                .placeHolder(R.color.abc_theme_black_7f)
                .imgView(view)
                .build());
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
