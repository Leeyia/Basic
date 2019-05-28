package com.dintech.api.scorpius;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoadView extends LinearLayout {

    private Paint paint = new Paint();
    private LoadScorpio scorpio;

    public LoadView(Context context) {
        this(context, null);
    }

    public LoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(getContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(50, 50);
        imageView.setLayoutParams(lp);
        scorpio = new LoadScorpio();
        imageView.setBackground(scorpio);
        addView(imageView);

        TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(12);
        textView.setText("加载中");
        addView(textView);
    }

    public void start() {
        scorpio.start();
    }

    public void stop() {
        scorpio.stop();
    }
}
