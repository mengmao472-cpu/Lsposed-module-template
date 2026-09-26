package com.example.module;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.Gravity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("iOS Bar Nextgen 运行中\n请在 LSPosed 中勾选 SystemUI 并重启手机");
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(18);
        setContentView(tv);
    }
}