package com.dintech.api.scorpius;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class LoadScorpio extends Scorpio {

    private Paint paint = new Paint();
    private float[] fractions = new float[]{
            1f, 1f, 1f, 1f
    };

    LoadScorpio() {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        /*width/7 = 4个四个矩形 + 3个留白*/
        int moveX = getBounds().width() / (fractions.length * 2 - 1);
        int moveY = getBounds().height() / 2;

        for (int i = 0; i < fractions.length; i++) {
            canvas.save();
            canvas.translate(i * moveX * 2 + moveX / 2, moveY);
            canvas.scale(1f, fractions[i]);
            RectF rectF = new RectF(-moveX / 2, -moveY, moveX / 2, moveY);
            canvas.drawRoundRect(rectF, 5, 5, paint);
            canvas.restore();
        }
    }

    @Override
    List<ValueAnimator> animators() {
        List<ValueAnimator> animators = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int index = i;
            ValueAnimator animator = ValueAnimator.ofFloat(1f, 0.1f, 1f);
            animator.setDuration(900L);
            animator.setRepeatCount(-1);
            animator.setStartDelay(i * 200);
            animator.addUpdateListener(valueAnimator -> {
                fractions[index] = (float) valueAnimator.getAnimatedValue();
                invalidateSelf();
            });
            animators.add(animator);
        }
        return animators;
    }
}
