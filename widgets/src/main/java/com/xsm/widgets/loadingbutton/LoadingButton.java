package com.xsm.widgets.loadingbutton;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.xsm.widgets.BaseView;
import com.xsm.widgets.R;

/**
 * Author: 夏胜明
 * Date: 2018/1/13 0013
 * Email: xiasem@163.com
 * Description:
 */

public class LoadingButton extends BaseView {

    private int progressSolidColor;
    private int progressStrokeColor;
    private float progressStrokeWidth;
    private float ringWidth;
    private int ringColor;
    private int iconId;

    public LoadingButton(Context context) {
        super(context);
        progressSolidColor = Color.parseColor("#303F9F");
        progressStrokeColor = Color.parseColor("#303F9F");
        iconId = R.drawable.widgets_result_success;
        init();
    }

    public LoadingButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton);
        progressSolidColor = typedArray.getColor(R.styleable.LoadingButton_widgetsLoadingButtonProgressSolidColor, Color.parseColor("#303F9F"));
        progressStrokeColor = typedArray.getColor(R.styleable.LoadingButton_widgetsLoadingButtonProgressStrokeColor, Color.parseColor("#303F9F"));
        progressStrokeWidth = typedArray.getDimension(R.styleable.LoadingButton_widgetsLoadingButtonProgressStrokeWidth, dp2px(2));
        ringWidth = typedArray.getDimension(R.styleable.LoadingButton_widgetsLoadingButtonProgressRingWidth, (int) dp2px(3));
        ringColor = typedArray.getColor(R.styleable.LoadingButton_widgetsLoadingButtonProgressRingColor, Color.parseColor("#ffffff"));
        iconId = typedArray.getResourceId(R.styleable.LoadingButton_widgetsLoadingButtonSuccessIcon, R.drawable.widgets_result_success);
        typedArray.recycle();
        init();
    }

    private Paint paint;
    private Paint progressPaint;
    private int state = State.PROGRESS;

    private void init() {
        paint = new Paint();

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        maxRadious = Math.min(avaliableHeight, avaliableWidth) / 2 - progressStrokeWidth;
    }
    private float maxRadious;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (state) {
            case State.PROGRESS:
                //绘制外圆
                progressPaint.setStyle(Paint.Style.STROKE);
                progressPaint.setColor(progressStrokeColor);
                canvas.drawCircle(centerX, centerY, maxRadious, progressPaint);
                //绘制圆环
                progressPaint.setColor(ringColor);
                progressPaint.setStrokeWidth(ringWidth);
                canvas.drawCircle(centerX, centerY, maxRadious - progressStrokeWidth - ringWidth, progressPaint);
                //绘制内圆
                progressPaint.setColor(progressSolidColor);
                postInvalidateDelayed(100);
                break;
            case State.COMPLETE:

                break;
            case State.ERROR:

                break;
        }
    }


    public class State {
        public static final int PROGRESS = 1;
        public static final int COMPLETE = 2;
        public static final int ERROR = 3;
    }
}

