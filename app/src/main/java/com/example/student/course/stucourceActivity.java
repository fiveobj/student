package com.example.student.course;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.student.MainActivity;
import com.example.student.R;
import com.example.student.contacts.ContactsFragment;
import com.example.student.home.homeFragment;
import com.example.student.my.myFragment;
import com.example.student.work.workFragment;

public class stucourceActivity extends AppCompatActivity {
private ImageButton recourse,news,job,detail,live;
private ImageButton back;
private TextView retv,ntv,jtv,dtv,ltv;
private FragmentManager manager;
private FragmentTransaction transaction;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stucource);
        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        recourse=(ImageButton)findViewById(R.id.stucourse_recourse);
        news=(ImageButton)findViewById(R.id.stucourse_new);
        job=(ImageButton)findViewById(R.id.stucourse_job);
        detail=(ImageButton)findViewById(R.id.stucourse_detail);
        live=(ImageButton)findViewById(R.id.stucourse_live);
        back=(ImageButton)findViewById(R.id.stucourse_back);

        retv=(TextView)findViewById(R.id.stucourse_recoursetv);
        ntv=(TextView)findViewById(R.id.stucourse_newtv);
        jtv=(TextView)findViewById(R.id.stucourse_jobtv);
        dtv=(TextView)findViewById(R.id.stucourse_detailtv);
        ltv=(TextView)findViewById(R.id.stucourse_livetv);

        recourse.setBackgroundResource(R.mipmap.stucourse_resource1);
        retv.setTextColor(Color.parseColor("#1E90FF"));

        //添加监听事件
        setListeners();

        //设置默认显示页面
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.stucourse_layout,new recourseFragment());
        transaction.commit();


    }


    private void setListeners(){
        OnClick onClick=new OnClick();
        recourse.setOnClickListener(onClick);
        news.setOnClickListener(onClick);
        job.setOnClickListener(onClick);
        live.setOnClickListener(onClick);
        detail.setOnClickListener(onClick);
        back.setOnClickListener(onClick);
    }


    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ResetImg();//改变顶部Tab为默认黑色
            transaction=manager.beginTransaction();
            switch (v.getId()){
                case R.id.stucourse_recourse:
                    transaction.replace(R.id.stucourse_layout,new recourseFragment());
                    recourse.setBackgroundResource(R.mipmap.stucourse_resource1);
                    retv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.stucourse_new:
                    transaction.replace(R.id.stucourse_layout,new newsFragment());
                    news.setBackgroundResource(R.mipmap.stucourse_new1);
                    ntv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.stucourse_job:
                    transaction.replace(R.id.stucourse_layout,new jobFragment());
                    job.setBackgroundResource(R.mipmap.stucourse_job1);
                    jtv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.stucourse_live:
                    transaction.replace(R.id.stucourse_layout,new liveFragment());
                    live.setBackgroundResource(R.mipmap.stucourse_live1);
                    ltv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.stucourse_detail:
                    transaction.replace(R.id.stucourse_layout,new detailFragment());
                    detail.setBackgroundResource(R.mipmap.stucourse_detail1);
                    dtv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.stucourse_back:
                    stucourceActivity.this.finish();
                    break;
                default:
                    break;
            }
            transaction.commit();
        }
    }


    private void ResetImg(){
        recourse.setBackgroundResource(R.mipmap.stucourse_resource);
        news.setBackgroundResource(R.mipmap.stucourse_new);
        job.setBackgroundResource(R.mipmap.stucourse_job);
        live.setBackgroundResource(R.mipmap.stucourse_live);
        detail.setBackgroundResource(R.mipmap.stucourse_detail);
        retv.setTextColor(Color.parseColor("#565657"));
        dtv.setTextColor(Color.parseColor("#565657"));
        jtv.setTextColor(Color.parseColor("#565657"));
        ltv.setTextColor(Color.parseColor("#565657"));
        ntv.setTextColor(Color.parseColor("#565657"));
    }
}