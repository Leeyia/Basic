package com.android.core.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.core.R;
import com.github.ybq.android.spinkit.style.DoubleBounce;
/**
 * @author: 蜡笔小新
 * @date: 2016-06-01 17:58
 * @GitHub: https://github.com/meikoz
 */
public class LoadingView extends Dialog implements View.OnClickListener {

    int[] colors = new int[]{
            android.graphics.Color.parseColor("#D55400"),
            android.graphics.Color.parseColor("#2B3E51"),
            android.graphics.Color.parseColor("#00BD9C"),
            android.graphics.Color.parseColor("#227FBB"),
            android.graphics.Color.parseColor("#7F8C8D"),
            android.graphics.Color.parseColor("#FFCC5C"),
            android.graphics.Color.parseColor("#D55400"),
            android.graphics.Color.parseColor("#1AAF5D"),
    };

    public LoadingView(Context context) {
        super(context, R.style.loading_dialog_style);
        onInit();
    }

    private void onInit() {
        View view = getLayoutInflater().inflate(R.layout.abc_view_loading, null);

        ProgressBar mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        DoubleBounce doubleBounce = new DoubleBounce();
        doubleBounce.setBounds(0, 0,
                100,
                100);
        doubleBounce.setColor(colors[7]);
        mProgressBar.setIndeterminateDrawable(doubleBounce);

        view.setOnClickListener(this);
        setContentView(view);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }

    @Override
    public void show() {
        super.show();
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }
}