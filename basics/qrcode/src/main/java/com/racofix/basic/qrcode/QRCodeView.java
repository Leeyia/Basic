package com.racofix.basic.qrcode;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class QRCodeView extends SurfaceView {

    public QRCodeView(Context context) {
        super(context);
    }

    public QRCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QRCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInitialize();
    }

    private void onInitialize() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
