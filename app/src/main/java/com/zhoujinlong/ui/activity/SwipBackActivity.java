package com.zhoujinlong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.core.ui.BaseSwipeBackActivity;
import com.android.core.widget.SwipeBackLayout;
import com.zhoujinlong.R;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class SwipBackActivity extends BaseSwipeBackActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, SwipBackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
    }
}
