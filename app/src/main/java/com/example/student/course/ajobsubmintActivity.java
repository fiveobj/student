package com.example.student.course;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.student.R;
import com.xuexiang.xui.widget.alpha.XUIAlphaButton;

import java.util.Calendar;

public class ajobsubmintActivity extends AppCompatActivity {

    private XUIAlphaButton data;
    private TextView tv;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajobsubmint);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        tv=(TextView)findViewById(R.id.data_select_tv);

        data=(XUIAlphaButton)findViewById(R.id.data_select);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(ajobsubmintActivity.this, DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, (TextView) tv, Calendar.getInstance());
            }
        });
    }

    public void showDatePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        new DatePickerDialog(context
                , themeResId
                , (view, year, monthOfYear, dayOfMonth) -> tv.setText(String.format("%d-%d-%d", year, (monthOfYear + 1), dayOfMonth))
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }
}