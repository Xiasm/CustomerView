package com.xsm.widgets.chargingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.xsm.widgets.BaseView;
import com.xsm.widgets.R;

/**
 * Author: 夏胜明
 * Date: 2018/3/9 0009
 * Email: xiasem@163.com
 * Description:
 */

public class ChargingView extends BaseView {
    private int outCircleColor;
    private float strokeWidth;
    private float spac;

    private Paint mPaint;
    private Paint mDegreePaint;
    private float radious;
    private Path mPath = new Path();
    private int degree = 0;

    public ChargingView(Context context) {
        super(context);
        outCircleColor = Color.parseColor("#e5e5e5");
        strokeWidth = 5;
        spac = 10;
        init();
    }

    public ChargingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChargingView);
        outCircleColor = typedArray.getColor(R.styleable.ChargingView_widgetsChargingViewOutCircleColor, Color.parseColor("#e5e5e5"));
        strokeWidth = typedArray.getDimension(R.styleable.ChargingView_widgetsChargingViewStrokeWidth, 5);
        spac = typedArray.getDimension(R.styleable.ChargingView_widgetsChargingViewSpac, 10);
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setColor(outCircleColor);

        mDegreePaint = new Paint();
        mDegreePaint.setAntiAlias(true);
        mDegreePaint.setStyle(Paint.Style.FILL);
        mDegreePaint.setColor(Color.parseColor("#FFD7C00E"));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radious = Math.min(avaliableWidth, avaliableHeight) / 2 - strokeWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, centerY, radious, mPaint);
        canvas.drawText("105", centerX, centerY, mPaint);
        canvas.save();

        canvas.rotate(getDegree(), centerX, centerY);
        canvas.drawCircle(centerX, centerY, radious - spac - strokeWidth, mPaint);
        //上三角
        mPath.moveTo(mPaddingLeft + avaliableWidth / 2, mPaddingTop + strokeWidth * 2 + spac + 30);
        mPath.lineTo(mPaddingLeft + avaliableWidth / 2 - 10, mPaddingTop + strokeWidth * 2 + spac);
        mPath.lineTo(mPaddingLeft + avaliableWidth / 2 + 10, mPaddingTop + strokeWidth * 2 + spac);
        mPath.close();
        canvas.drawPath(mPath, mDegreePaint);
        //下三角
        mPath.reset();
        mPath.moveTo(mPaddingLeft + avaliableWidth / 2, mPaddingTop + avaliableHeight - strokeWidth * 2 - spac - 30);
        mPath.lineTo(mPaddingLeft + avaliableWidth / 2 - 10, mPaddingTop + avaliableHeight - strokeWidth * 2 - spac);
        mPath.lineTo(mPaddingLeft + avaliableWidth / 2 + 10, mPaddingTop + avaliableHeight - strokeWidth * 2 - spac);
        mPath.close();
        canvas.drawPath(mPath, mDegreePaint);
        postInvalidateDelayed(100);
    }

    private int getDegree() {
        degree += 20;
        if (degree > 360) {
            degree -= 360;
        }
        return degree;
    }

    private static final String TAG = "ChargingView";
}
