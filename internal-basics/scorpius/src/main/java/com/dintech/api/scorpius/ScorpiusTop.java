package com.dintech.api.scorpius;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ScorpiusTop extends LinearLayout {

    private float[] scaleYFloats = new float[]{
            0.25f,
            0.50f,
            0.75f,
            1.00f
    };

    private Paint paint = new Paint();
    private TextView scorpius_tip_tv;
    private String mScorpiusText;
    private float mPercent;

    public ScorpiusTop(Context context) {
        this(context, null);
    }

    public ScorpiusTop(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScorpius(attrs);
    }

    private void initScorpius(AttributeSet attrs) {

        @SuppressLint("CustomViewStyleable")
        TypedArray ty = getContext().obtainStyledAttributes(attrs, R.styleable.Scorpius_Top);
        @ColorInt int mScorpiusColor = ty.getColor(R.styleable.Scorpius_Top_scorpius_color, getResources().getColor(R.color.scorpius_color));
        ColorStateList scorpius_text_color = ty.getColorStateList(R.styleable.Scorpius_Top_scorpius_text_color);

        mScorpiusText = ty.getString(R.styleable.Scorpius_Top_scorpius_text);
        ty.recycle();

        paint.setColor(mScorpiusColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        View scorpius_top = LayoutInflater.from(getContext()).inflate(R.layout.scorpius_top, this);
        ImageView scorpius_top_iv = scorpius_top.findViewById(R.id.scorpius_top_iv);
        scorpius_tip_tv = scorpius_top.findViewById(R.id.scorpius_tip_tv);

        setScorpiusText(mScorpiusText);
        scorpius_tip_tv.setTextColor(scorpius_text_color);
        scorpius_top_iv.setBackground(scorpius);
    }

    public void setScorpiusText(String text) {
        this.mScorpiusText = text;
        scorpius_tip_tv.setText(mScorpiusText);
    }

    public void setPercent(float percent) {
        this.mPercent = percent;
        float max = Math.min(1f, Math.abs(mPercent));
        Log.d("ssssss", percent + " " + max);
    }

    public void start() {
        if (scorpius != null) scorpius.start();
    }

    public void stop() {
        if (scorpius != null) scorpius.stop();
    }

    private Scorpius scorpius = new Scorpius() {
        @Override
        public void draw(Canvas canvas) {
            float translateX = getWidth() / 11;
            float translateY = getHeight() / 2;

            Log.d("ssssss", " mPercent:" + mPercent + " getWidth:" + getWidth() + " getHeight:" + getHeight());
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
                    postInvalidate();
                });
                animators.add(scaleAnim);
            }
            return animators;
        }
    };

    public void reset() {
        this.scaleYFloats = new float[]{
                0.25f,
                0.50f,
                0.75f,
                1.00f
        };
        setScorpiusText(mScorpiusText);
    }
}
