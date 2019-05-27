package com.dintech.api.scorpius;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LoadScorpio extends Scorpio {

    private Paint paint = new Paint();
    private float[] scales = new float[]{
            1f, 1f, 1f, 1f
    };

    public LoadScorpio() {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = getBounds().width();
        int height = getBounds().height();
        int item_width = width / 7;

        for (int i = 0; i < 4; i++) {
            canvas.save();
//            canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
            canvas.scale(1f, scales[i]);
            Log.d("222222", scales[i] + " ");
            Rect rect = new Rect(i * 2 * item_width, 0, (i * 2 + 1) * item_width, height);
            canvas.drawRect(rect, paint);
            canvas.restore();
        }
    }

    @Override
    List<ValueAnimator> animators() {
        List<ValueAnimator> animators = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int index = i;
            ValueAnimator animator = ValueAnimator.ofFloat(1f, 0.1f, 1f);
            animator.setDuration(1000L);
            animator.setRepeatCount(-1);
            animator.setStartDelay(i * 250);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Log.d("111111", valueAnimator.getAnimatedValue() + " ");
                    scales[index] = (float) valueAnimator.getAnimatedValue();
                    invalidateSelf();
                }
            });
            animators.add(animator);
        }
        return animators;
    }
}
