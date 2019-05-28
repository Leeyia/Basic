package com.dintech.api.scorpius;

import android.animation.ValueAnimator;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.List;

public abstract class Scorpius extends Drawable implements Animatable {

    private int alpha;
    private boolean isReady;
    private List<ValueAnimator> mAnimators;
    private Rect drawBounds = new Rect();
    private HashMap<ValueAnimator, ValueAnimator.AnimatorUpdateListener> mListenerMap = new HashMap<>();

    abstract List<ValueAnimator> animators();

    /*当View的大小位置改变时回调*/
    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.drawBounds = bounds;
    }

    /*设置当前Drawable的透明度*/
    @Override
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    @Override
    public int getAlpha() {
        return alpha;
    }

    /*设置颜色滤镜*/
    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    /*返回Drawable的具体类型，可以是透明、半透明、全透明*/
    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public void start() {
        onReadyAnimators();

        // If the animators has not ended, do nothing.
        if (isStarted()) {
            return;
        }
        startAnimators();
    }

    @Override
    public void stop() {
        stopAnimators();
    }

    private void startAnimators() {
        for (int i = 0; i < mAnimators.size(); i++) {
            ValueAnimator animator = mAnimators.get(i);
            //when the animator restart , add the updateListener again because they
            // was removed by animator stop .
            ValueAnimator.AnimatorUpdateListener updateListener = mListenerMap.get(animator);
            if (updateListener != null) {
                animator.addUpdateListener(updateListener);
            }
            animator.start();
        }
    }

    private void stopAnimators() {
        if (mAnimators != null) {
            for (ValueAnimator animator : mAnimators) {
                if (animator != null && animator.isStarted()) {
                    animator.removeAllUpdateListeners();
                    animator.end();
                }
            }
        }
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    private void onReadyAnimators() {
        if (!isReady) {
            this.mAnimators = animators();
            if (mAnimators == null) return;
            this.isReady = true;
        }
    }

    private boolean isStarted() {
        for (ValueAnimator animator : mAnimators) {
            return animator.isStarted();
        }
        return false;
    }

    public void addUpdateListener(ValueAnimator animator, ValueAnimator.AnimatorUpdateListener updateListener) {
        this.mListenerMap.put(animator, updateListener);
    }
}
