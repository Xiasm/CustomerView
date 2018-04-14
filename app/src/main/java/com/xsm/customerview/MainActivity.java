package com.xsm.customerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.xsm.widgets.timepicker.TimePickerView;
import com.xsm.widgets.timepicker.adapter.ArrayWheelAdapter;
import com.xsm.widgets.timepicker.builder.TimePickerBuilder;
import com.xsm.widgets.timepicker.listener.OnTimeSelectListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TimePickerView mPvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        WheelView wheelView = findViewById(R.id.wheelview);

        wheelView.setCyclic(false);

        final List<String> mOptionsItems = new ArrayList<>();
        mOptionsItems.add("item0");
        mOptionsItems.add("item1");
        mOptionsItems.add("item2");

//        wheelView.setAdapter(new ArrayWheelAdapter<String>(mOptionsItems));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Toast.makeText(MainActivity.this, "index=" + mOptionsItems.get(index), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void selectData(View view) {
        mPvTime.show();
    }

    private void init() {
        //选中事件回调
//取消按钮文字
//确认按钮文字
//标题文字大小
//标题文字
//点击屏幕，点在控件外部范围时，是否取消显示
//是否循环滚动
//标题文字颜色
//确定按钮文字颜色
//取消按钮文字颜色
//标题背景颜色 Night mode
//滚轮背景颜色 Night mode
//是否显示为对话框样式
        mPvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String format = dateFormat.format(date);
                Toast.makeText(MainActivity.this, format, Toast.LENGTH_SHORT).show();
            }
        })

//                .setCancelText("取消")//取消按钮文字
//                .setSubmitText("确定")//确认按钮文字
//                .setTitleSize(20)//标题文字大小
//                .setTitleText("选择时间")//标题文字
//                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(false)//是否循环滚动
//                .setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
                .setCustomerDate(new Date(System.currentTimeMillis()))
                .setCustomerNum(60)
//                .isDialog(false)//是否显示为对话框样式
                .build();
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
