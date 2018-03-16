package com.xiasem.spark.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.xiasem.spark.R;

/**
 * Author: 夏胜明
 * Date: 2018/3/13 0013
 * Email: xiasem@163.com
 * Description:
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        setNavigationBar();
    }

    protected void setStatusBar() {
        setStatusBar(R.color.statusBarDefaultColor);
    }

    protected void setStatusBar(@ColorRes int colorResId) {
        StatusBarUtil.setColor(this, getResources().getColor(colorResId), 0);
    }

    protected void setNavigationBar() {
        setNavigationBar(R.color.navigationBarDefaultColor);
    }

    protected void setNavigationBar(@ColorRes int colorResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(colorResId));
        }
    }

}
