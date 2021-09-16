package com.example.student.my.exp;

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

public class myexpActivity extends AppCompatActivity {

    private TextView jobtv,pasttv,projecttv;
    private ImageButton job,past,project,back;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myexp);


        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        back=(ImageButton)findViewById(R.id.myexp_back);
        job=(ImageButton)findViewById(R.id.myexp_job);
        project=(ImageButton)findViewById(R.id.myexp_project);
        past=(ImageButton)findViewById(R.id.myexp_pasttime);

        jobtv=(TextView)findViewById(R.id.myexp_jobtv);
        pasttv=(TextView)findViewById(R.id.myexp_pasttimetv);
        projecttv=(TextView)findViewById(R.id.myexp_projecttv);

        job.setImageResource(R.mipmap.myexp_job1);
        jobtv.setTextColor(Color.parseColor("#1E90FF"));
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.myexp_layout,new myexp_jobFragment());
        transaction.commit();

        setListners();

    }

    private void setListners(){
        OnClick onClick=new OnClick();
        job.setOnClickListener(onClick);
        project.setOnClickListener(onClick);
        past.setOnClickListener(onClick);
        back.setOnClickListener(onClick);

    }


    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ResetImg();
            transaction=manager.beginTransaction();
            switch (v.getId()){
                case R.id.myexp_back:
                    myexpActivity.this.finish();
                    break;
                case R.id.myexp_job:
                    transaction.replace(R.id.myexp_layout,new myexp_jobFragment());
                    job.setImageResource(R.mipmap.myexp_job1);
                    jobtv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.myexp_project:
                    transaction.replace(R.id.myexp_layout,new myexp_projectFragment());
                    project.setImageResource(R.mipmap.myexp_project1);
                    projecttv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.myexp_pasttime:
                    transaction.replace(R.id.myexp_layout,new myexp_pasttimeFragment());
                    past.setImageResource(R.mipmap.myexp_pasttime1);
                    pasttv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                default:
                    break;
            }
            transaction.commit();
        }
    }


    public void ResetImg(){
        job.setImageResource(R.mipmap.myexp_job);
        jobtv.setTextColor(Color.parseColor("#000000"));
        project.setImageResource(R.mipmap.myexp_project);
        projecttv.setTextColor(Color.parseColor("#000000"));
        past.setImageResource(R.mipmap.myexp_pasttime);
        pasttv.setTextColor(Color.parseColor("#000000"));
    }
}