package com.dintech.api.scorpius;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ScorpiusTop extends LinearLayout {

    private Scorpius scorpius;
    public float[] scaleYFloats = new float[]{1.0f,
            1.0f,
            1.0f,
            1.0f,
            1.0f,};

    public ScorpiusTop(Context context) {
        this(context, null);
    }

    public ScorpiusTop(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScorpiusTop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initScorpius();
    }

    private void initScorpius() {
        scorpius = new Scorpius(getContext()) {
            @Override
            public void dispatchDraw(Canvas canvas, Paint paint) {
                float translateX = getWidth() / 11;
                float translateY = getHeight() / 2;
                for (int i = 0; i < 5; i++) {
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
                long[] delays = new long[]{500, 250, 0, 250, 500};
                for (int i = 0; i < 5; i++) {
                    final int index = i;
                    ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.3f, 1);
                    scaleAnim.setDuration(1000);
                    scaleAnim.setRepeatCount(-1);
                    scaleAnim.setStartDelay(delays[i]);
                    addUpdateListener(scaleAnim, new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            scaleYFloats[index] = (float) animation.getAnimatedValue();
                            Log.d("sssasasaassasa", scaleYFloats[index] + "");
                            postInvalidate();
                        }
                    });
                    animators.add(scaleAnim);
                }
                return animators;
            }
        };

        View scorpius_top = LayoutInflater.from(getContext()).inflate(R.layout.custom_view_scorpius_top, this);
        ImageView scorpius_top_iv = scorpius_top.findViewById(R.id.scorpius_top_iv);
        scorpius_top_iv.setBackground(scorpius);
    }

    public Scorpius getScorpius() {
        return scorpius;
    }
}
