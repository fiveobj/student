package com.example.student;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.contacts.ContactsFragment;
import com.example.student.course.courseFragment;
import com.example.student.fab.FabJobActivity;
import com.example.student.fab.FabNoticeActivity;
import com.example.student.fab.FabPastActivity;
import com.example.student.fab.FabProjectActivity;
import com.example.student.fab.FabStuActivity;
import com.example.student.home.homeFragment;
import com.example.student.my.myFragment;
import com.example.student.work.workFragment;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity<onClick> extends AppCompatActivity {
private ImageButton home,course,work,contacts,my;
private TextView tv_home,tv_course,tv_work,tv_contacts,tv_my;
private FragmentManager manager;
private FragmentTransaction transaction;
private RelativeLayout lead;
private FloatingActionButton notice,stu,job,pasttime,project;
private FloatingActionMenu menu;
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
        tv_home=(TextView)findViewById(R.id.tv_home);
        tv_course=(TextView)findViewById(R.id.tv_course);
        tv_work=(TextView)findViewById(R.id.tv_work);
        tv_contacts=(TextView)findViewById(R.id.tv_contacts);
        tv_my=(TextView)findViewById(R.id.tv_my);
        notice=(FloatingActionButton)findViewById(R.id.fab_notice);
        stu=(FloatingActionButton)findViewById(R.id.fab_stu);
        job=(FloatingActionButton)findViewById(R.id.fab_job);
        pasttime=(FloatingActionButton)findViewById(R.id.fab_pasttime);
        project=(FloatingActionButton)findViewById(R.id.fab_project);
        menu=(FloatingActionMenu)findViewById(R.id.fab_menu);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        //添加监听事件
        setListeners();
        //设置默认显示页面为主页
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.main_layout,new homeFragment());
        transaction.commit();
        //设置导航栏背景颜色
        lead=(RelativeLayout)findViewById(R.id.lead);
        lead.setBackgroundColor(Color.parseColor("#FFFFFF"));

        home.setBackgroundResource(R.mipmap.home1);
        tv_home.setTextColor(Color.parseColor("#1E90FF"));



    }
    private void setListeners(){
        OnClick onClick=new OnClick();
        home.setOnClickListener(onClick);
        contacts.setOnClickListener(onClick);
        course.setOnClickListener(onClick);
        work.setOnClickListener(onClick);
        my.setOnClickListener(onClick);
        notice.setOnClickListener(onClick);
        job.setOnClickListener(onClick);
        stu.setOnClickListener(onClick);
        pasttime.setOnClickListener(onClick);
        project.setOnClickListener(onClick);
        menu.setOnClickListener(onClick);

    }

private class OnClick implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        ResetImg();//改变底部Tab为默认黑色
        transaction=manager.beginTransaction();
        Intent intent;
        switch (v.getId()){
            case R.id.home:
                transaction.replace(R.id.main_layout,new homeFragment());
                home.setBackgroundResource(R.mipmap.home1);
                tv_home.setTextColor(Color.parseColor("#1E90FF"));
                break;
            case R.id.course:
                transaction.replace(R.id.main_layout,new courseFragment());
                course.setBackgroundResource(R.mipmap.course1);
                tv_course.setTextColor(Color.parseColor("#1E90FF"));
                break;
            case R.id.work:
                transaction.replace(R.id.main_layout,new workFragment());
                work.setBackgroundResource(R.mipmap.work1);
                tv_work.setTextColor(Color.parseColor("#1E90FF"));
                break;
            case R.id.contacts:
                transaction.replace(R.id.main_layout,new ContactsFragment());
                contacts.setBackgroundResource(R.mipmap.contacts1);
                tv_contacts.setTextColor(Color.parseColor("#1E90FF"));
                break;
            case R.id.my:
                transaction.replace(R.id.main_layout,new myFragment());
                my.setBackgroundResource(R.mipmap.my1);
                tv_my.setTextColor(Color.parseColor("#1E90FF"));
                break;
            case R.id.fab_notice:
                intent=new Intent(MainActivity.this, FabNoticeActivity.class);
                startActivity(intent);
                menu.toggle(false);
                break;
            case R.id.fab_stu:
                intent=new Intent(MainActivity.this, FabStuActivity.class);
                startActivity(intent);
                menu.toggle(false);
                break;
            case R.id.fab_project:
                intent=new Intent(MainActivity.this, FabJobActivity.class);
                startActivity(intent);
                menu.toggle(false);
                break;
            case R.id.fab_pasttime:
                intent=new Intent(MainActivity.this, FabPastActivity.class);
                startActivity(intent);
                menu.toggle(false);
                break;
            case R.id.fab_job:
                intent=new Intent(MainActivity.this, FabProjectActivity.class);
                startActivity(intent);
                menu.toggle(false);
                break;
            default:
                break;
        }

        transaction.commit();
    }
}
    private void ResetImg()
    {
        home.setBackgroundResource(R.mipmap.home);
        course.setBackgroundResource(R.mipmap.course);
        work.setBackgroundResource(R.mipmap.work);
        contacts.setBackgroundResource(R.mipmap.contacts);
        my.setBackgroundResource(R.mipmap.my);
        tv_my.setTextColor(Color.parseColor("#565657"));
        tv_contacts.setTextColor(Color.parseColor("#565657"));
        tv_course.setTextColor(Color.parseColor("#565657"));
        tv_work.setTextColor(Color.parseColor("#565657"));
        tv_home.setTextColor(Color.parseColor("#565657"));
    }



}