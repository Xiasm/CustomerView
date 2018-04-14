package com.xsm.widgets.timepicker.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Author: 夏胜明
 * Date: 2018/4/14 0014
 * Email: xiasem@163.com
 * Description:
 */

public class TimeCarrier {
    public static final int TYPE_DAY = 1;
    public static final int TYPE_HOUR = 2;
    public static final int TYPE_MINUTE = 3;

    private String showStr;
    private int type;
    private long time;
    private String hh;
    private String mm;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TimeCarrier) {
            TimeCarrier carrier = (TimeCarrier) obj;
            if (this.showStr.equals(carrier.showStr)) {
                return true;
            }
        }
        return false;
    }

    public TimeCarrier() {
    }

    public TimeCarrier(String showStr, int type) {
        this.showStr = showStr;
        this.type = type;
    }

    public TimeCarrier(String showStr, int type, long time) {
        this.showStr = showStr;
        this.type = type;
        this.time = time;
    }

    public String getTimeFormatStr() {
        String s;
        if (type == TYPE_DAY) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            s = format.format(new Date(time));
        } else if (type == TYPE_HOUR) {
            s = " " + hh;
        } else {
            s = ":" + mm;
        }
        return s;
    }

    public String getShowStr() {
        return showStr;
    }

    public void setShowStr(String showStr) {
        this.showStr = showStr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getHh() {
        return hh;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }
}
