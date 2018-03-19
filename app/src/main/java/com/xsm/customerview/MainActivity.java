package com.xsm.customerview;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;

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
                    number++;
                    if (number < 100) {
                        mCountdownView.setCurrentProgress(number);
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    } else {
                        mCountdownView.setCurrentProgress(number);
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
        number = 0;
        mCountdownView.setTotalProgress(100);
        mCountdownView.setCurrentProgress(0);
        mHandler.sendEmptyMessageDelayed(1, 1000);

    }

    @Override
    protected void setStatusBar() {
        setStatusBar(R.color.colorAccent);
    }

    public void next(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
