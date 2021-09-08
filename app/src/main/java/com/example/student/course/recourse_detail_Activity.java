package com.example.student.course;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.student.MainActivity;
import com.example.student.R;

public class recourse_detail_Activity extends AppCompatActivity {

    private ImageButton add,back;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recourse_detail_);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        add=findViewById(R.id.recourse_addclass_ib);
        back=findViewById(R.id.recourse_back);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(recourse_detail_Activity.this,"加入成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(recourse_detail_Activity.this,MainActivity.class);
                //将课程添加到在学课程中

                intent.putExtra("id",1);
                startActivity(intent);
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               recourse_detail_Activity.this.finish();
            }
        });
    }
}