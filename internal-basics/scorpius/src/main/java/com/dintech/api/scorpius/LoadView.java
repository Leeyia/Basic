package com.dintech.api.scorpius;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
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
    private LoadScorpius scorpio;
    private int widthPixel, heightPixel, intervalPixel;
    private int textSize, imageColor, textColor, textTipRes;
    private String textTip;

    public LoadView(Context context) {
        this(context, null);
    }

    public LoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        @SuppressLint("CustomViewStyleable") TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.Load_View);
        intervalPixel = t.getDimensionPixelSize(R.styleable.Load_View_scorpius_interval, 5);
        widthPixel = t.getDimensionPixelSize(R.styleable.Load_View_scorpius_image_width, 50);
        heightPixel = t.getDimensionPixelSize(R.styleable.Load_View_scorpius_image_height, 50);

        textSize = t.getDimensionPixelSize(R.styleable.Load_View_scorpius_text_size, 12);
        imageColor = t.getColor(R.styleable.Load_View_scorpius_image_color, Color.RED);
        textColor = t.getColor(R.styleable.Load_View_scorpius_text_color, Color.RED);
        textTipRes = t.getResourceId(R.styleable.Load_View_scorpius_text_tip_res, R.string.scorpius_tip_text);

        t.recycle();
        init();
    }

    private void init() {
        paint.setColor(imageColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(getContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(widthPixel, heightPixel);
        imageView.setLayoutParams(lp);
        scorpio = new LoadScorpius(paint);
        imageView.setBackground(scorpio);
        addView(imageView);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.topMargin = intervalPixel;

        TextView textView = new TextView(getContext());
        textView.setLayoutParams(lp2);
        textView.setTextColor(textColor);
        textView.setTextSize(textSize);
        textView.setText(textTipRes);
        addView(textView);
    }

    public void start() {
        scorpio.start();
    }

    public void stop() {
        scorpio.stop();
    }
}
