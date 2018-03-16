package com.xsm.customerview;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import com.xiasem.spark.activity.BaseActivity;
import com.xsm.widgets.countdownView.CountdownView;


public class MainActivity extends BaseActivity {

    private CountdownView mCountdownView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    number--;
                    if (number > 0) {
                        mCountdownView.setCurrentProgress(number);
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    } else {
                        mCountdownView.setCurrentProgress(0);
                    }
                    break;
            }
        }
    };

    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCountdownView = findViewById(R.id.countdownView);
        number = 60;
        mCountdownView.setTotalProgress(60);
        mHandler.sendEmptyMessageDelayed(1, 2000);

    }

    @Override
    protected void setStatusBar() {
        setStatusBar(R.color.colorAccent);
    }
}
