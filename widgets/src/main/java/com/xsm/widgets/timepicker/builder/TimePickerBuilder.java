package com.xsm.widgets.timepicker.builder;

import android.content.Context;

import com.xsm.widgets.timepicker.TimePickerView;
import com.xsm.widgets.timepicker.configure.PickerOptions;
import com.xsm.widgets.timepicker.listener.OnTimeSelectListener;

import java.util.Date;

/**
 * Created by xiaosongzeem on 2018/3/20.
 */

public class TimePickerBuilder {

    private PickerOptions mPickerOptions;

    //Required
    public TimePickerBuilder(Context context, OnTimeSelectListener listener) {
        mPickerOptions = new PickerOptions(PickerOptions.TYPE_PICKER_TIME);
        mPickerOptions.context = context;
        mPickerOptions.timeSelectListener = listener;
    }

    public TimePickerBuilder setCustomerDate(Date date) {
        mPickerOptions.customerDate = date;
        return this;
    }

    public TimePickerBuilder setCustomerNum(int num) {
        mPickerOptions.customerNum = num;
        return this;
    }

    public TimePickerView build() {
        return new TimePickerView(mPickerOptions);
    }
}
