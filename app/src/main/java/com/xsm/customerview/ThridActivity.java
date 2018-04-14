package com.xsm.customerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ThridActivity extends AppCompatActivity {
    public static final String INTENT_ORDER_SN = "orderSn";
    public static final String INTENT_CHARGE_ORDER_SN = "charge_order_sn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
    }

    public void show(View view) {
        Toast.makeText(this, "ordersn=" + getIntent().getStringExtra(INTENT_ORDER_SN) + "       chargeOrderSn=" + getIntent().getStringExtra(INTENT_CHARGE_ORDER_SN), Toast.LENGTH_SHORT).show();
    }
}
