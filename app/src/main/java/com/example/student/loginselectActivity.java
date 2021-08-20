package com.example.student;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class loginselectActivity extends AppCompatActivity {
private ImageButton back,stulogin;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginselect);
        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        stulogin=(ImageButton)findViewById(R.id.stu_login);
        back=(ImageButton)findViewById(R.id.logins_back);
        setListeners();
    }
    private void setListeners(){
        OnClick onClick=new OnClick();
        stulogin.setOnClickListener(onClick);
        back.setOnClickListener(onClick);
    }
    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=null;
            switch (v.getId())
            {
                case R.id.stu_login:
                    intent=new Intent(loginselectActivity.this,stuloginActivity.class);
                    break;
                case R.id.logins_back:
                    intent=new Intent(loginselectActivity.this,signinActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}