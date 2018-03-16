package com.xsm.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: 夏胜明
 * Date: 2018/3/16 0009
 * Email: xiasem@163.com
 * Description:
 */

public abstract class BaseView extends View {
    //View宽高设为wrap_content时的默认值
    protected int width_AT_MOST = 200;
    protected int height_AT_MOST = 200;

    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mPaddingTop;
    protected int mPaddingBottom;
    protected int mWidth;
    protected int mHeight;
    protected int avaliableWidth;
    protected int avaliableHeight;

    //View除去Padding的中心点
    protected float centerX, centerY;

    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        mPaddingLeft = left;
        mPaddingTop = top;
        mPaddingRight = right;
        mPaddingBottom = bottom;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width_AT_MOST, height_AT_MOST);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width_AT_MOST, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, height_AT_MOST);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        mPaddingTop = getPaddingTop();
        mPaddingBottom = getPaddingBottom();
        mWidth = w;
        mHeight = h;
        avaliableWidth = mWidth - mPaddingLeft - mPaddingRight;
        avaliableHeight = mHeight - mPaddingTop - mPaddingBottom;
        centerX = mPaddingLeft + mWidth / 2;
        centerY = mPaddingTop + mHeight / 2;
    }
}
