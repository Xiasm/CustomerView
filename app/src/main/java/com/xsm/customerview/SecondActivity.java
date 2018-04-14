package com.xsm.customerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {
    public static final String INTENT_ORDER_SN = "orderSn";
    public static final String INTENT_CHARGE_ORDER_SN = "charge_order_sn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }


    public void next(View view) {
        Intent intent = new Intent(this, ThridActivity.class);
        intent.putExtra(ThridActivity.INTENT_ORDER_SN, getIntent().getStringExtra(INTENT_ORDER_SN));
        intent.putExtra(ThridActivity.INTENT_CHARGE_ORDER_SN, getIntent().getStringExtra(INTENT_CHARGE_ORDER_SN));
        startActivity(intent);
    }
}
