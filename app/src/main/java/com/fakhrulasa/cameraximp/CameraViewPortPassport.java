package com.fakhrulasa.cameraximp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CameraViewPortPassport extends ViewGroup {

    public CameraViewPortPassport(Context context) {
        super(context);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public CameraViewPortPassport(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public CameraViewPortPassport(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int viewportMargin = 40;
        int viewportCornerRadius = 15;
        Paint eraser = new Paint();
        eraser.setAntiAlias(true);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        float widthFrame = (float) getWidth() - viewportMargin;
        float heightFrame = (3 * widthFrame) / 3;

        float frameCenter = getHeight() / 2;
        float frameTop = frameCenter - (heightFrame / 2);
        float frameBottom = frameCenter + (heightFrame / 2);

        RectF rect = new RectF((float) viewportMargin+80, (float) frameTop, widthFrame-(float)80,  frameBottom);
        RectF frame = new RectF((float) viewportMargin - 2+80, (float) frameTop - 2, widthFrame - 82, frameBottom + 4);
        Path path = new Path();
        Paint stroke = new Paint();
        stroke.setAntiAlias(true);
        stroke.setStrokeWidth(20);
        stroke.setColor(Color.WHITE);
        stroke.setStyle(Paint.Style.STROKE);
        path.addRoundRect(frame, (float) viewportCornerRadius, (float) viewportCornerRadius, Path.Direction.CW);
        canvas.drawPath(path, stroke);
        canvas.drawRoundRect(rect, (float) viewportCornerRadius, (float) viewportCornerRadius, eraser);
    }
}