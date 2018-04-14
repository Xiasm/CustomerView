package com.xsm.widgets.timepicker.configure;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;

import com.xsm.widgets.R;
import com.xsm.widgets.timepicker.listener.OnTimeSelectListener;
import com.xsm.widgets.timepicker.wheelview.view.WheelView;

import java.util.Date;

/**
 * 配置类
 * Created by xiaosongzeem on 2018/3/8.
 */

public class PickerOptions {

    //常量
    private static final int PICKER_VIEW_BTN_COLOR_NORMAL = 0xFF057dff;
    private static final int PICKER_VIEW_BG_COLOR_TITLE = 0xFFf5f5f5;
    private static final int PICKER_VIEW_COLOR_TITLE = 0xFF000000;
    private static final int PICKER_VIEW_BG_COLOR_DEFAULT = 0xFFFFFFFF;

    public static final int TYPE_PICKER_OPTIONS = 1;
    public static final int TYPE_PICKER_TIME = 2;


    public OnTimeSelectListener timeSelectListener;

    public boolean cyclic = false;//是否循环
    public boolean isLunarCalendar = false;//是否显示农历

    public String label_year, label_month, label_day, label_hours, label_minutes, label_seconds;//单位
    public int x_offset_year, x_offset_month, x_offset_day, x_offset_hours, x_offset_minutes, x_offset_seconds;//单位


    public PickerOptions(int buildType) {
        if (buildType == TYPE_PICKER_OPTIONS) {
            layoutRes = R.layout.pickerview_options;
        } else {
            layoutRes = R.layout.pickerview_time;
        }
    }

    //******* 公有字段  ******//
    public int layoutRes;
    public ViewGroup decorView;
    public int textGravity = Gravity.CENTER;
    public Context context;

    public String textContentConfirm;//确定按钮文字
    public String textContentCancel;//取消按钮文字
    public String textContentTitle;//标题文字

    public int textColorConfirm = PICKER_VIEW_BTN_COLOR_NORMAL;//确定按钮颜色
    public int textColorCancel = PICKER_VIEW_BTN_COLOR_NORMAL;//取消按钮颜色
    public int textColorTitle = PICKER_VIEW_COLOR_TITLE;//标题颜色

    public int bgColorWheel = PICKER_VIEW_BG_COLOR_DEFAULT;//滚轮背景颜色
    public int bgColorTitle = PICKER_VIEW_BG_COLOR_TITLE;//标题背景颜色

    public int textSizeSubmitCancel = 17;//确定取消按钮大小
    public int textSizeTitle = 18;//标题文字大小
    public int textSizeContent = 18;//内容文字大小

    public int textColorOut = 0xFFa8a8a8; //分割线以外的文字颜色
    public int textColorCenter = 0xFF2a2a2a; //分割线之间的文字颜色
    public int dividerColor = 0xFFd5d5d5; //分割线的颜色
    public int backgroundId = -1; //显示时的外部背景色颜色,默认是灰色

    public float lineSpacingMultiplier = 1.6f; // 条目间距倍数 默认1.6

    public boolean cancelable = true;//是否能取消
    public boolean isCenterLabel = true;//是否只显示中间的label,默认每个item都显示
    public Typeface font = Typeface.MONOSPACE;//字体样式
    public WheelView.DividerType dividerType = WheelView.DividerType.FILL;//分隔线类型

    public Date customerDate;
    public int customerNum;

}
