package com.example.student.course;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.student.R;

public class MainActivity extends AppCompatActivity {
ImageButton home,course,work,contacts,my;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home=(ImageButton)findViewById(R.id.home);
        course=(ImageButton)findViewById(R.id.course);
        work=(ImageButton)findViewById(R.id.work);
        contacts=(ImageButton)findViewById(R.id.contacts);
        my=(ImageButton)findViewById(R.id.my);
        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }
}