package com.example.student.course;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.student.R;
import com.example.student.customclass.formDialog;

public class informActivity extends AppCompatActivity {

    private ImageButton button1;
    private ImageView new1,back;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);


        button1=findViewById(R.id.infrom_btn1);
        new1=findViewById(R.id.inform_newis1);
        back=findViewById(R.id.inform_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(informActivity.this,stucourceActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formDialog formDialog=new formDialog(informActivity.this);
                formDialog.setTitle("停课通知");
                formDialog.setDetail("受台风天气影响，今天不上课，同行们在寝室减少外出，关好门窗，有特殊情况联系辅导员或班主任。");
                formDialog.setClose(new formDialog.IOnCanceListener() {
                    @Override
                    public void onCancel(com.example.student.customclass.formDialog dialog) {
                        Toast.makeText(informActivity.this,"已读",Toast.LENGTH_SHORT).show();

                        new1.setVisibility(View.GONE);
                    }
                });
                formDialog.show();
            }
        });

    }
}