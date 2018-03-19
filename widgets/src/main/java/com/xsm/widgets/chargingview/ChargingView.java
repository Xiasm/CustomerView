package com.xsm.widgets.chargingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
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
    private TextPaint mTextPaint;
    private Paint mDegreePaint;
    private float radious;
    private Path mPath = new Path();
    private int degree = 0;

    private int chargeLevelTextColor;
    private float chargeLevelTextSize;
    private int chargeLevelTopTextColor;
    private float chargeLevelTopTextSize;
    private int chargeLevelBottomTextColor;
    private float chargeLevelBottomTextSize;

    private String chargeLevelText = "123456";
    private String chargeTopText = "充电量";
    private String chargeBottomText = "度/kw.h";

    public ChargingView(Context context) {
        super(context);
        outCircleColor = Color.parseColor("#e5e5e5");
        strokeWidth = 5;
        spac = 10;
        chargeLevelTextColor = Color.parseColor("#3d3d3d");
        chargeLevelTextSize = dp2px(30);
        chargeLevelTopTextColor = Color.parseColor("#3d3d3d");
        chargeLevelTopTextSize = dp2px(15);
        chargeLevelBottomTextColor = Color.parseColor("#3d3d3d");
        chargeLevelBottomTextSize = dp2px(15);
        init();
    }

    public ChargingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChargingView);
        outCircleColor = typedArray.getColor(R.styleable.ChargingView_widgetsChargingViewOutCircleColor, Color.parseColor("#e5e5e5"));
        strokeWidth = typedArray.getDimension(R.styleable.ChargingView_widgetsChargingViewStrokeWidth, 5);
        spac = typedArray.getDimension(R.styleable.ChargingView_widgetsChargingViewSpac, 10);
        chargeLevelTextColor = typedArray.getColor(R.styleable.ChargingView_widgetsChargingViewChargeLevelTextColor, Color.parseColor("#3d3d3d"));
        chargeLevelTextSize = typedArray.getDimension(R.styleable.ChargingView_widgetsChargingViewChargeLevelTextSize, dp2px(30));
        chargeLevelTopTextColor = typedArray.getColor(R.styleable.ChargingView_widgetsChargingViewChargeLevelTopTextColor, Color.parseColor("#3d3d3d"));
        chargeLevelTopTextSize = typedArray.getDimension(R.styleable.ChargingView_widgetsChargingViewChargeLevelTopTextSize, dp2px(15));
        chargeLevelBottomTextColor = typedArray.getColor(R.styleable.ChargingView_widgetsChargingViewChargeLevelBottomTextColor, Color.parseColor("#3d3d3d"));
        chargeLevelBottomTextSize = typedArray.getDimension(R.styleable.ChargingView_widgetsChargingViewChargeLevelBottomTextSize, dp2px(15));
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

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setStyle(Paint.Style.FILL);

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

        //绘制中间文字
        mTextPaint.setTextSize(chargeLevelTextSize);
        mTextPaint.setColor(chargeLevelTextColor);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        //为基线到字体上边框的距离,即上图中的top
        float top = fontMetrics.top;
        //为基线到字体下边框的距离,即上图中的bottom
        float bottom = fontMetrics.bottom;
        int baseLineX = mPaddingLeft + avaliableWidth / 2;
        float baseLineY = mPaddingTop + avaliableHeight / 2 - bottom / 2 - top / 2;
        canvas.drawText(chargeLevelText, baseLineX, baseLineY, mTextPaint);

        float textHeight = fontMetrics.descent - fontMetrics.ascent;
        //绘制上边文字
        mTextPaint.setTextSize(chargeLevelTopTextSize);
        mTextPaint.setColor(chargeLevelTopTextColor);
        fontMetrics = mTextPaint.getFontMetrics();
        top = fontMetrics.top;
        bottom = fontMetrics.bottom;
        float textHeight1 = fontMetrics.descent - fontMetrics.ascent;
        baseLineY = mPaddingTop + avaliableHeight / 2 - textHeight / 2 - spacTop - textHeight1 / 2 - bottom / 2 - top / 2;
        canvas.drawText(chargeTopText, baseLineX, baseLineY, mTextPaint);
        //绘制下边文字
        mTextPaint.setTextSize(chargeLevelBottomTextSize);
        mTextPaint.setColor(chargeLevelBottomTextColor);
        fontMetrics = mTextPaint.getFontMetrics();
//        top = fontMetrics.top;
//        bottom = fontMetrics.bottom;
        float textHeight2 = fontMetrics.descent - fontMetrics.ascent;
        baseLineY = mPaddingTop + avaliableHeight / 2 + textHeight / 2 + spacBottom + textHeight2 / 2;
        canvas.drawText(chargeBottomText, baseLineX, baseLineY, mTextPaint);

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
    private float spacTop = dp2px(10);
    private float spacBottom = dp2px(10);
    private int getDegree() {
        degree += 10;
        if (degree > 360) {
            degree -= 360;
        }
        return degree;
    }

    public String getChargeLevelText() {
        return chargeLevelText;
    }

    public void setChargeLevelText(String chargeLevelText) {
        this.chargeLevelText = chargeLevelText;
    }

    private static final String TAG = "ChargingView";
}
