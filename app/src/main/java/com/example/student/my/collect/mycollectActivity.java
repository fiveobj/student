package com.example.student.my.collect;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.student.R;


public class mycollectActivity extends AppCompatActivity {

    private ImageButton job,pasttime,fair,project,back;
    private TextView job_tv,pasttime_tv,fair_tv,project_tv;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private collect_jobFragment jobFragment;
    private collect_fairFragment fairFragment;
    private collect_projectFragment projectFragment;
    private collect_pasttimeFragment pasttimeFragment;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollect);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);


        job=(ImageButton)findViewById(R.id.collect_job);
        fair=(ImageButton)findViewById(R.id.collect_fair);
        pasttime=(ImageButton)findViewById(R.id.collect_pasttime);
        project=(ImageButton)findViewById(R.id.collect_project);
        back=(ImageButton)findViewById(R.id.collect_back);

        job_tv=(TextView)findViewById(R.id.collect_jobtv);
        fair_tv=(TextView)findViewById(R.id.collect_fairtv);
        pasttime_tv=(TextView)findViewById(R.id.collect_pasttimetv);
        project_tv=(TextView)findViewById(R.id.collect_projecttv);

        setListeners();


        //manager=getFragmentManager();
        //transaction=manager.beginTransaction();
        //transaction.add(R.id.collect_layout,new collect_jobFragment());
        //transaction.commit();

        initJobFragment();



    }

    private void initJobFragment(){
        transaction=getFragmentManager().beginTransaction();
        if(jobFragment==null){
            jobFragment=new collect_jobFragment();
            transaction.add(R.id.collect_layout,jobFragment);
        }
        job.setImageResource(R.mipmap.my_collect_job);
        job_tv.setTextColor(Color.parseColor("#1E90FF"));
        hideFragment(transaction);
        transaction.show(jobFragment);
        transaction.commit();
    }
    private void initFairFragment(){
        transaction=getFragmentManager().beginTransaction();
        if(fairFragment==null){
            fairFragment=new collect_fairFragment();
            transaction.add(R.id.collect_layout,fairFragment);
        }
        fair.setImageResource(R.mipmap.my_collect_fair);
        fair_tv.setTextColor(Color.parseColor("#1E90FF"));
        hideFragment(transaction);
        transaction.show(fairFragment);
        transaction.commit();
    }
    private void initPasttimeFragment(){
        transaction=getFragmentManager().beginTransaction();
        if(pasttimeFragment==null){
            pasttimeFragment=new collect_pasttimeFragment();
            transaction.add(R.id.collect_layout,pasttimeFragment);
        }
        pasttime.setImageResource(R.mipmap.my_collect_parttime);
        pasttime_tv.setTextColor(Color.parseColor("#1E90FF"));
        hideFragment(transaction);
        transaction.show(pasttimeFragment);
        transaction.commit();
    }
    private void initProjectFragment(){
        transaction=getFragmentManager().beginTransaction();
        if(projectFragment==null){
            projectFragment=new collect_projectFragment();
            transaction.add(R.id.collect_layout,projectFragment);
        }
        project.setImageResource(R.mipmap.my_collect_project);
        project_tv.setTextColor(Color.parseColor("#1E90FF"));
        hideFragment(transaction);
        transaction.show(projectFragment);
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction){
        if(jobFragment!=null){
            transaction.hide(jobFragment);
        }
        if(projectFragment!=null){
            transaction.hide(projectFragment);
        }
        if(pasttimeFragment!=null){
            transaction.hide(pasttimeFragment);
        }
        if(fairFragment!=null){
            transaction.hide(fairFragment);
        }
    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        job.setOnClickListener(onClick);
        pasttime.setOnClickListener(onClick);
        fair.setOnClickListener(onClick);
        project.setOnClickListener(onClick);
        back.setOnClickListener(onClick);
    }


    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ResetImg();
            if(v==job)initJobFragment();
            if(v==pasttime)initPasttimeFragment();
            if(v==project)initProjectFragment();
            if(v==fair)initFairFragment();
            if(v==back)mycollectActivity.this.finish();
            /*transaction=manager.beginTransaction();
            switch (v.getId()){
                case R.id.collect_job:
                    initJobFragment();
                    job.setImageResource(R.mipmap.my_collect_job);
                    job_tv.setTextColor(Color.parseColor("1E90FF"));
                    break;
                case R.id.collect_pasttime:
                    initPasttimeFragment();
                    pasttime.setImageResource(R.mipmap.my_collect_parttime);
                    pasttime_tv.setTextColor(Color.parseColor("1E90FF"));
                    break;
                case R.id.collect_fair:
                    initFairFragment();
                    fair.setImageResource(R.mipmap.my_collect_fair);
                    fair_tv.setTextColor(Color.parseColor("1E90FF"));
                    break;
                case R.id.collect_project:
                    initProjectFragment();
                    project.setImageResource(R.mipmap.my_collect_project);
                    project_tv.setTextColor(Color.parseColor("1E90FF"));
                    break;
                case R.id.collect_back:
                    mycollectActivity.this.finish();
                    break;
                default:
                    break;

            }*/
        }
    }

    private void ResetImg(){
        job.setImageResource(R.mipmap.my_collect_job1);
        fair.setImageResource(R.mipmap.my_collect_fair1);
        pasttime.setImageResource(R.mipmap.my_collect_pasttime1);
        project.setImageResource(R.mipmap.my_collect_project1);
        job_tv.setTextColor(Color.parseColor("#696969"));
        fair_tv.setTextColor(Color.parseColor("#696969"));
        pasttime_tv.setTextColor(Color.parseColor("#696969"));
        project_tv.setTextColor(Color.parseColor("#696969"));
    }

}