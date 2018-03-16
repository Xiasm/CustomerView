package com.xsm.widgets.countdownView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.xsm.widgets.R;

/**
 * Author: 夏胜明
 * Date: 2018/3/9 0009
 * Email: xiasem@163.com
 * Description:
 */

public class CountdownView extends View {
    private int strokeColor;
    private float strokeWidth;
    private int loadingColor;
    private float loadingWidth;
    private int numberColor;
    private float numberSize;
    private String numberSuffixText;
    private int numberSuffixColor;
    private float numberSuffixSize;

    private int mWidth;
    private int mHeight;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPaddingBottom;

    private float totalProgress;
    private float currentProgress;

    private Paint mPaint;
    private TextPaint mTextPaint;
    private RectF mProgressRect;

    public CountdownView(Context context) {
        super(context);
        strokeColor = Color.parseColor("#b7b5b5");
        strokeWidth = 5;
        loadingColor = Color.parseColor("#ffffff");
        loadingWidth = 10;
        numberColor = Color.parseColor("#3d3d3d");
        numberSize = dp2px(22);
        numberSuffixText = "s";
        numberSuffixColor = numberColor;
        numberSuffixSize = dp2px(15);
        init();
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountdownView);
        strokeColor = typedArray.getColor(R.styleable.CountdownView_widgetsCountdownViewStrokeColor, Color.parseColor("#b7b5b5"));
        strokeWidth = typedArray.getDimension(R.styleable.CountdownView_widgetsCountdownViewStrokeWidth, 5);
        loadingColor = typedArray.getColor(R.styleable.CountdownView_widgetsCountdownViewLoadingColor, Color.parseColor("#ffffff"));
        loadingWidth = typedArray.getDimension(R.styleable.CountdownView_widgetsCountdownViewLoadingWidth, 10);
        numberColor = typedArray.getColor(R.styleable.CountdownView_widgetsCountdownViewNumberColor, Color.parseColor("#3d3d3d"));
        numberSize = typedArray.getDimension(R.styleable.CountdownView_widgetsCountdownViewNumberSize, dp2px(22));
        numberSuffixText = typedArray.getString(R.styleable.CountdownView_widgetsCountdownViewNumberSuffixText);
        numberSuffixColor = typedArray.getColor(R.styleable.CountdownView_widgetsCountdownViewNumberSuffixColor, Color.parseColor("#3d3d3d"));
        numberSuffixSize = typedArray.getDimension(R.styleable.CountdownView_widgetsCountdownViewNumberSuffixSize, dp2px(15));
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        mProgressRect = new RectF();
        mTextPaint = new TextPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 200);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        mPaddingTop = getPaddingTop();
        mPaddingBottom = getPaddingBottom();
        mWidth = w - mPaddingLeft - mPaddingRight;
        mHeight = h - mPaddingTop - mPaddingBottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1.绘制边框
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setColor(strokeColor);
        float radious = Math.min(mWidth, mHeight) / 2 - strokeWidth;
        //cx：圆心的x坐标。cy：圆心的y坐标。radius：圆的半径。
        canvas.drawCircle(mPaddingLeft + mWidth / 2, mPaddingTop + mHeight / 2, radious, mPaint);
        //2.绘制进度框
        mPaint.setStrokeWidth(loadingWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(loadingColor);
        mProgressRect.set(mPaddingLeft + loadingWidth - strokeWidth, mPaddingTop + loadingWidth - strokeWidth, mPaddingLeft + mWidth - loadingWidth + strokeWidth, mPaddingTop + mHeight - loadingWidth + strokeWidth);
        //RectF oval:圆弧的形状和大小的范围  float startAngle:设置圆弧是从哪个角度来顺时针绘画的(0是正三点方向) float sweepAngle：设置圆弧扫过的角度 boolean useCenter：设置我们的圆弧在绘画的时候，是否经过圆形（这个参数在我们的 mPaint.setStyle(Paint.Style.STROKE); 设置为描边属性的时候，是看不出效果的。）
        float sweepAngle = ((totalProgress - currentProgress) / totalProgress) * 360;
        canvas.drawArc(mProgressRect, -90, sweepAngle, false, mPaint);
        //3.绘制文字
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(numberSize);
        mTextPaint.setColor(numberColor);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        //为基线到字体上边框的距离,即上图中的top
        float top = fontMetrics.top;
        //为基线到字体下边框的距离,即上图中的bottom
        float bottom = fontMetrics.bottom;
        float baseLineY = mPaddingTop + mHeight / 2 - bottom / 2 - top / 2;
        String text = String.valueOf((int) currentProgress);
        float numberTextWidth = mTextPaint.measureText(text);
        canvas.drawText(text, mPaddingLeft + mWidth / 2, baseLineY, mTextPaint);
        //绘制读秒文字的后缀
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextSize(numberSuffixSize);
        mTextPaint.setColor(numberSuffixColor);
        canvas.drawText(numberSuffixText, mPaddingLeft + mWidth / 2 + numberTextWidth / 2, baseLineY, mTextPaint);

    }

    public float getTotalProgress() {
        return totalProgress;
    }

    public void setTotalProgress(float totalProgress) {
        this.totalProgress = totalProgress;
    }

    public float getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(float currentProgress) {
        this.currentProgress = currentProgress;
        postInvalidate();
    }

    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
