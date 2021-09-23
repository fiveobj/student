package com.example.student.my.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.student.R;

import java.util.ArrayList;
import java.util.List;

public class mycourseActivity extends AppCompatActivity {

    private TextView start,unstart,finish;
    private ListView courselist;
    private mycourse_startAdapter adapter;
    private List<mycourse_startitem> list;
    private Context context;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycourse);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        context=this;

        start=(TextView)findViewById(R.id.mycourse_start);
        unstart=(TextView)findViewById(R.id.mycourse_unstart);
        finish=(TextView)findViewById(R.id.mycourse_finish);
        courselist=(ListView)findViewById(R.id.mycourse_list);
        back=(ImageButton)findViewById(R.id.course_back);

        //默认显示
        start.setTextColor(Color.parseColor("#1E90FF"));
        list=getDatastart();
        adapter=new mycourse_startAdapter(this,R.layout.mycourse_startitemlayout,list);
        courselist.setAdapter(adapter);

        setListener();


    }

    public void setListener(){
        OnClick onClick=new OnClick();
        start.setOnClickListener(onClick);
        unstart.setOnClickListener(onClick);
        finish.setOnClickListener(onClick);
        back.setOnClickListener(onClick);
    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            setR();
            switch (v.getId()){
                case R.id.mycourse_start:
                    start.setTextColor(Color.parseColor("#1E90FF"));
                    list=getDatastart();
                    break;
                case R.id.mycourse_unstart:
                    unstart.setTextColor(Color.parseColor("#1E90FF"));
                    list=getDataunstart();
                    break;
                case R.id.mycourse_finish:
                    finish.setTextColor(Color.parseColor("#1E90FF"));
                    list=getDatafinish();
                    break;
                case R.id.course_back:
                    mycourseActivity.this.finish();
                default:
                    break;
            }
            adapter=new mycourse_startAdapter(context,R.layout.mycourse_startitemlayout,list);
            courselist.setAdapter(adapter);
        }
    }

    public void setR(){
        start.setTextColor(Color.parseColor("#000000"));
        unstart.setTextColor(Color.parseColor("#000000"));
        finish.setTextColor(Color.parseColor("#000000"));
    }

    public List<mycourse_startitem> getDatastart(){
        List<mycourse_startitem> item=new ArrayList<mycourse_startitem>();
        item.add(new mycourse_startitem("暑假服务外包集训","2021.7.4-2021.12.3","张三",R.drawable.logo));
        item.add(new mycourse_startitem("暑假服务外包集训","2021.7.4-2021.12.3","张三",R.drawable.logo));

        return item;
    }


    public List<mycourse_startitem> getDataunstart(){
        List<mycourse_startitem> item=new ArrayList<mycourse_startitem>();
        item.add(new mycourse_startitem("暑假服务外包集训","2021.7.4-2021.12.3","张三",R.drawable.logo));

        return item;
    }


    public List<mycourse_startitem> getDatafinish(){
        List<mycourse_startitem> item=new ArrayList<mycourse_startitem>();
        item.add(new mycourse_startitem("暑假服务外包集训","2021.7.4-2021.12.3","张三",R.drawable.logo));
        item.add(new mycourse_startitem("暑假服务外包集训","2021.7.4-2021.12.3","张三",R.drawable.logo));
        item.add(new mycourse_startitem("暑假服务外包集训","2021.7.4-2021.12.3","张三",R.drawable.logo));
        item.add(new mycourse_startitem("暑假服务外包集训","2021.7.4-2021.12.3","张三",R.drawable.logo));

        return item;
    }
}