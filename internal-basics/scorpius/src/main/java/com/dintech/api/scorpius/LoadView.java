package com.dintech.api.scorpius;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class LoadView extends LinearLayout {

    private float[] scaleYFloats = new float[]{
            1f,
            1f,
            1f,
            1f
    };

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
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(70, 60);
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
        scorpius.stop();
    }

    private Scorpius scorpius = new Scorpius() {
        @Override
        public void draw(Canvas canvas) {
            float translateX = getWidth() / 11;
            float translateY = getHeight() / 2;

            for (int i = 0; i < 4; i++) {
                canvas.save();
                canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
                canvas.scale(1.0f, scaleYFloats[i]);
                RectF rectF = new RectF(-translateX / 2, -getHeight() / 2.5f, translateX / 2, getHeight() / 2.5f);
                canvas.drawRoundRect(rectF, 5, 5, paint);
                canvas.restore();
            }
        }

        @Override
        public ArrayList<ValueAnimator> animators() {
            ArrayList<ValueAnimator> animators = new ArrayList<>();
            long[] delays = new long[]{0, 200, 400, 600};
            for (int i = 0; i < 4; i++) {
                final int index = i;
                ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.2f, 1);
                scaleAnim.setDuration(1300);
                scaleAnim.setRepeatCount(-1);
                scaleAnim.setStartDelay(delays[i]);
                addUpdateListener(scaleAnim, animation -> {
                    scaleYFloats[index] = (float) animation.getAnimatedValue();
                    invalidate();
                    postInvalidate();
                });
                animators.add(scaleAnim);
            }
            return animators;
        }
    };
}
