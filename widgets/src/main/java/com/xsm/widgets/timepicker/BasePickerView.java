package com.xsm.widgets.timepicker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.xsm.widgets.R;
import com.xsm.widgets.timepicker.configure.PickerOptions;
import com.xsm.widgets.timepicker.listener.OnDismissListener;
import com.xsm.widgets.timepicker.utils.PickerViewAnimateUtil;

/**
 * Author: 夏胜明
 * Date: 2018/4/14 0014
 * Email: xiasem@163.com
 * Description:
 */

public class BasePickerView {
    private Context context;
    protected ViewGroup contentContainer;
    private ViewGroup rootView;//附加View 的 根View

    protected PickerOptions mPickerOptions;
    private OnDismissListener onDismissListener;
    private boolean dismissing;

    private Animation outAnim;
    private Animation inAnim;
    private boolean isShowing;

    protected int animGravity = Gravity.BOTTOM;

    protected View clickView;//是通过哪个View弹出的
    private boolean isAnim = true;

    public BasePickerView(Context context) {
        this.context = context;
    }

    protected void initViews() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //如果只是要显示在屏幕的下方
        //decorView是activity的根View,包含 contentView 和 titleView
        if (mPickerOptions.decorView == null) {
            mPickerOptions.decorView = (ViewGroup) ((Activity) context).getWindow().getDecorView();
        }
        //将控件添加到decorView中
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_basepickerview, mPickerOptions.decorView, false);
        rootView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (mPickerOptions.backgroundId != -1) {
            rootView.setBackgroundColor(mPickerOptions.backgroundId);
        }
        //这个是真正要加载时间选取器的父布局
        contentContainer = (ViewGroup) rootView.findViewById(R.id.content_container);
        contentContainer.setLayoutParams(params);
        setKeyBackCancelable(true);
    }

    protected void initAnim() {
        inAnim = getInAnimation();
        outAnim = getOutAnimation();
    }

    protected void initEvents() {
    }

    public Animation getInAnimation() {
        int res = PickerViewAnimateUtil.getAnimationResource(this.animGravity, true);
        return AnimationUtils.loadAnimation(context, res);
    }

    public Animation getOutAnimation() {
        int res = PickerViewAnimateUtil.getAnimationResource(this.animGravity, false);
        return AnimationUtils.loadAnimation(context, res);
    }

    private View.OnKeyListener onKeyBackListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_DOWN && isShowing()) {
                dismiss();
                return true;
            }
            return false;
        }
    };

    public View findViewById(int id) {
        return contentContainer.findViewById(id);
    }

    public void setKeyBackCancelable(boolean isCancelable) {
        ViewGroup View;
        View = rootView;

        View.setFocusable(isCancelable);
        View.setFocusableInTouchMode(isCancelable);
        if (isCancelable) {
            View.setOnKeyListener(onKeyBackListener);
        } else {
            View.setOnKeyListener(null);
        }
    }

    /**
     * Called when the user touch on black overlay, in order to dismiss the dialog.
     */
    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                dismiss();
            }
            return false;
        }
    };

    protected BasePickerView setOutSideCancelable(boolean isCancelable) {

        if (rootView != null) {
            View view = rootView.findViewById(R.id.outmost_container);

            if (isCancelable) {
                view.setOnTouchListener(onCancelableTouchListener);
            } else {
                view.setOnTouchListener(null);
            }
        }

        return this;
    }

    public void show(boolean isAnim) {
        this.isAnim = isAnim;
        show();
    }

    public void show(View v) {
        this.clickView = v;
        show();
    }


    /**
     * 添加View到根视图
     */
    public void show() {
        if (isShowing()) {
            return;
        }
        isShowing = true;
        onAttached(rootView);
        rootView.requestFocus();
    }

    /**
     * show的时候调用
     *
     * @param view 这个View
     */
    private void onAttached(View view) {
        mPickerOptions.decorView.addView(view);
        if (isAnim) {
            contentContainer.startAnimation(inAnim);
        }
    }

    /**
     * 检测该View是不是已经添加到根视图
     *
     * @return 如果视图已经存在该View返回true
     */
    public boolean isShowing() {
        return rootView.getParent() != null || isShowing;
    }

    public void dismiss() {
        if (dismissing) {
            return;
        }
        if (isAnim) {
            //消失动画
            outAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    dismissImmediately();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            contentContainer.startAnimation(outAnim);
        } else {
            dismissImmediately();
        }
        dismissing = true;

    }

    public void dismissImmediately() {

        mPickerOptions.decorView.post(new Runnable() {
            @Override
            public void run() {
                //从根视图移除
                mPickerOptions.decorView.removeView(rootView);
                isShowing = false;
                dismissing = false;
                if (onDismissListener != null) {
                    onDismissListener.onDismiss(BasePickerView.this);
                }
            }
        });


    }
}
