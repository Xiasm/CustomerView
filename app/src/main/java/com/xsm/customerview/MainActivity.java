package com.xsm.customerview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xsm.countdownview.CountdownView;


public class MainActivity extends AppCompatActivity {

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


}
