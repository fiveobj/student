package com.example.student.my.resume;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.student.R;

public class people_resumeActivity extends AppCompatActivity {
    private ImageButton down,share,change,method,back;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_resume);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        down=(ImageButton)findViewById(R.id.myresume_down);
        share=(ImageButton)findViewById(R.id.myresume_share);
        change=(ImageButton)findViewById(R.id.myresume_changedata);
        method=(ImageButton)findViewById(R.id.myresume_method);
        back=(ImageButton)findViewById(R.id.myresume_peo_change_back);

        setlistener();
    }

    private void setlistener(){
        OnClick onClick=new OnClick();
        down.setOnClickListener(onClick);
        share.setOnClickListener(onClick);
        change.setOnClickListener(onClick);
        method.setOnClickListener(onClick);
        back.setOnClickListener(onClick);
    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.myresume_down:
                case R.id.myresume_share:
                case R.id.myresume_changedata:
                    Intent intent=new Intent(people_resumeActivity.this,people_resume_changeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.myresume_method:
                case R.id.myresume_peo_change_back:
                    people_resumeActivity.this.finish();
                    break;
                default:
                    break;

            }
        }
    }
}