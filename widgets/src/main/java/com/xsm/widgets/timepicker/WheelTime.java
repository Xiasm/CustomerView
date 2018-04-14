package com.xsm.widgets.timepicker;

import android.view.View;

import com.xsm.widgets.R;
import com.xsm.widgets.timepicker.adapter.ArrayWheelAdapter;
import com.xsm.widgets.timepicker.model.TimeCarrier;
import com.xsm.widgets.timepicker.wheelview.listener.OnItemSelectedListener;
import com.xsm.widgets.timepicker.wheelview.view.WheelView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Author: 夏胜明
 * Date: 2018/4/8 0008
 * Email: xiasem@163.com
 * Description:
 */

public class WheelTime {
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private View view;
    private WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_minutes;

    private Date startDate;
    private List<TimeCarrier> days = new ArrayList<>();
    private List<TimeCarrier> hours = new ArrayList<>();
    private List<TimeCarrier> minutes = new ArrayList<>();
    private int currentDay = 0;
    private int currentHour = 0;
    private int currentMinute = 0;

    public WheelTime(View view) {
        super();
        this.view = view;
        setView(view);
    }

    public void setPicker(Date date, int num) {
        startDate = date;
        Date date1 = new Date();
        date1.setTime(date.getTime());
        setSolar(date1, num);
    }

    private static final String TAG = "WheelTime";
    private void setSolar(Date date, int num) {
        wv_minutes = (WheelView) view.findViewById(R.id.min);
        List<TimeCarrier> minute = getMinute();
        minutes.clear();
        minutes.addAll(minute);
        wv_minutes.setAdapter(new ArrayWheelAdapter<TimeCarrier>(minutes));

        wv_hours = (WheelView) view.findViewById(R.id.hour);
        List<TimeCarrier> hour = getHour(date.getHours());
        hours.clear();
        hours.addAll(hour);
        wv_hours.setAdapter(new ArrayWheelAdapter<TimeCarrier>(hours));

        wv_day = (WheelView) view.findViewById(R.id.day);
        List<TimeCarrier> day = getDay(date, num);
        days.clear();
        days.addAll(day);
        wv_day.setAdapter(new ArrayWheelAdapter<TimeCarrier>(days));

        wv_minutes.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                currentMinute = index;
            }
        });

        wv_hours.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                currentHour = index;
            }
        });

        wv_day.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (index == 0) {
                    wv_hours.setAdapter(new ArrayWheelAdapter<TimeCarrier>(getHour(startDate.getHours())));
                    wv_hours.setCurrentItem(0);
                } else {
                    wv_hours.setAdapter(new ArrayWheelAdapter<TimeCarrier>(getHour()));
                    wv_hours.setCurrentItem(0);
                }
                currentDay = index;
            }
        });

        wv_day.setCurrentItem(0);
        wv_hours.setCurrentItem(0);
        wv_minutes.setCurrentItem(0);

    }

    private List<TimeCarrier> getDay(Date date, int num) {
        ArrayList<TimeCarrier> list = new ArrayList<>();
        if (num <= 0) {
            return list;
        } else {
            Calendar calendar = new GregorianCalendar();
            for (int i = 0; i < num; i++) {
                TimeCarrier carrier = new TimeCarrier();
                carrier.setTime(date.getTime());
                carrier.setType(TimeCarrier.TYPE_DAY);

                calendar.setTime(date);
                StringBuilder builder = new StringBuilder();
                if (date.getMonth() <= 8) {
                    builder.append(0);
                }
                builder.append(date.getMonth() + 1).append("月");
                if (date.getDate() <= 9) {
                    builder.append(0);
                }
                builder.append(date.getDate()).append("日");

                if(Calendar.MONDAY == calendar.get(Calendar.DAY_OF_WEEK)){
                    builder.append(" 周一");
                } else if(Calendar.TUESDAY == calendar.get(Calendar.DAY_OF_WEEK)){
                    builder.append(" 周二");
                } else if(Calendar.WEDNESDAY == calendar.get(Calendar.DAY_OF_WEEK)){
                    builder.append(" 周三");
                } else if(Calendar.THURSDAY == calendar.get(Calendar.DAY_OF_WEEK)){
                    builder.append(" 周四");
                } else if(Calendar.FRIDAY == calendar.get(Calendar.DAY_OF_WEEK)){
                    builder.append(" 周五");
                } else if(Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)){
                    builder.append(" 周六");
                } else if(Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)){
                    builder.append(" 周日");
                } else {
                    builder.append(" 八宝粥");
                }
                carrier.setShowStr(builder.toString());
                list.add(carrier);
                //把日期往后增加一天.整数往后推,负数往前移动
                calendar.add(Calendar.DATE,1);
                //这个时间就是日期往后推一天的结果
                date=calendar.getTime();
            }
        }

        return list;
    }

    private List<TimeCarrier> getHour() {
        ArrayList<TimeCarrier> list = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            TimeCarrier timeCarrier = new TimeCarrier();
            timeCarrier.setType(TimeCarrier.TYPE_HOUR);
            timeCarrier.setShowStr(String.valueOf(i) + "点");
            if (i <= 9) {
                timeCarrier.setHh("0" + String.valueOf(i));
            } else {
                timeCarrier.setHh(String.valueOf(i));
            }
            list.add(timeCarrier);
        }
        return list;
    }

    private List<TimeCarrier> getHour(int currentHour) {
        ArrayList<TimeCarrier> list = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (i >= currentHour + 2) {
                TimeCarrier timeCarrier = new TimeCarrier();
                timeCarrier.setType(TimeCarrier.TYPE_HOUR);
                timeCarrier.setShowStr(String.valueOf(i) + "点");
                if (i <= 9) {
                    timeCarrier.setHh("0" + String.valueOf(i));
                } else {
                    timeCarrier.setHh(String.valueOf(i));
                }
                list.add(timeCarrier);
            }
        }
        return list;
    }

    private List<TimeCarrier> getMinute() {
        ArrayList<TimeCarrier> list = new ArrayList<>();
        TimeCarrier timeCarrier1 = new TimeCarrier();
        timeCarrier1.setType(TimeCarrier.TYPE_MINUTE);
        timeCarrier1.setShowStr("00分");
        timeCarrier1.setMm("00");
        list.add(timeCarrier1);

        TimeCarrier timeCarrier2 = new TimeCarrier();
        timeCarrier2.setType(TimeCarrier.TYPE_MINUTE);
        timeCarrier2.setShowStr("30分");
        timeCarrier2.setMm("30");
        list.add(timeCarrier2);

        return list;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getTime() {
        return days.get(wv_day.getCurrentItem()).getTimeFormatStr() +
                hours.get(wv_hours.getCurrentItem()).getTimeFormatStr() +
                minutes.get(wv_minutes.getCurrentItem()).getTimeFormatStr();
    }

}
