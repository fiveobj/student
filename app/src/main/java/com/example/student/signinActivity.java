package com.example.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class signinActivity extends AppCompatActivity {
    private EditText id,pass;
    private ImageButton signin;
    private TextView login;
    private String myid,mypass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //跳转注册页面
        login=(TextView)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signinActivity.this,loginselectActivity.class);
                startActivityForResult(intent,1);
            }
        });

        //定义密码账号控件
        id=(EditText)findViewById(R.id.signin_id);
        pass=(EditText)findViewById(R.id.signin_pass);

        //登录控件
        signin=(ImageButton)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的账号密码
                myid=id.getText().toString().trim();
                mypass=pass.getText().toString().trim();
                //数据传入接口与返回结果对比
                if(TextUtils.isEmpty(myid)){
                    Toast.makeText(signinActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(mypass)){
                    Toast.makeText(signinActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(signinActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent data=new Intent();
                    startActivity(new Intent(signinActivity.this,MainActivity.class));
                }
                /*else if(){//密码正确
                    Toast.makeText(signinActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent data=new Intent();
                    startActivity(new Intent(signinActivity.this,MainActivity.class));
                }else if(){//密码不正确
                    Toast.makeText(signinActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(signinActivity.this, "此用户不存在", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}