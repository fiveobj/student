package com.example.student.my.mysand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.student.R;
import com.example.student.my.course.mycourse_startitem;

import java.util.ArrayList;
import java.util.List;

public class mysandActivity extends AppCompatActivity {

    private TextView havesand,waitface,sandyes,sandno;
    private ListView listView;
    private MysandAdapter adapter;
    private List<MysandItem> list;
    private ImageButton back;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysand);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        havesand=(TextView)findViewById(R.id.mysand_hayesand);
        waitface=(TextView)findViewById(R.id.mysand_waitface);
        sandyes=(TextView)findViewById(R.id.mysand_yes);
        sandno=(TextView)findViewById(R.id.mysand_no);
        listView=(ListView)findViewById(R.id.mysand_list);
        back=(ImageButton)findViewById(R.id.mysand_back);
        context=this;

        havesand.setTextColor(Color.parseColor("#1E90FF"));
        list=getDatahavesand();
        adapter=new MysandAdapter(this,R.layout.mysand_itemlayout,list);
        listView.setAdapter(adapter);

        setListener();

    }

    public void setListener(){
        OnClick onClick=new OnClick();
        havesand.setOnClickListener(onClick);
        waitface.setOnClickListener(onClick);
        sandyes.setOnClickListener(onClick);
        sandno.setOnClickListener(onClick);
        back.setOnClickListener(onClick);

    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            reR();
            switch (v.getId()){
                case R.id.mysand_hayesand:
                    havesand.setTextColor(Color.parseColor("#1E90FF"));
                    list=getDatahavesand();
                    break;
                case R.id.mysand_waitface:
                    waitface.setTextColor(Color.parseColor("#1E90FF"));
                    list=getDatawaitface();
                    break;
                case R.id.mysand_yes:
                    sandyes.setTextColor(Color.parseColor("#1E90FF"));
                    list=getDatasandyes();
                    break;
                case R.id.mysand_no:
                    sandno.setTextColor(Color.parseColor("#1E90FF"));
                    list=getDatasandno();
                    break;
                case R.id.mysand_back:
                    mysandActivity.this.finish();
                    break;
                default:
                    break;

            }
            adapter=new MysandAdapter(context,R.layout.mysand_itemlayout,list);
            listView.setAdapter(adapter);

        }
    }

    public void reR(){
        havesand.setTextColor(Color.parseColor("#000000"));
        waitface.setTextColor(Color.parseColor("#000000"));
        sandyes.setTextColor(Color.parseColor("#000000"));
        sandno.setTextColor(Color.parseColor("#000000"));
    }

    public List<MysandItem> getDatahavesand(){
        List<MysandItem> item=new ArrayList<MysandItem>();
        item.add(new MysandItem("字节跳动","已投递","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","已投递","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","已投递","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","已投递","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));

        return item;
    }

    public List<MysandItem> getDatawaitface(){
        List<MysandItem> item=new ArrayList<MysandItem>();
        item.add(new MysandItem("字节跳动","待面试","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","待面试","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","待面试","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","待面试","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));

        return item;
    }

    public List<MysandItem> getDatasandyes(){
        List<MysandItem> item=new ArrayList<MysandItem>();
        item.add(new MysandItem("字节跳动","录用","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","录用","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","录用","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","录用","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));

        return item;
    }

    public List<MysandItem> getDatasandno(){
        List<MysandItem> item=new ArrayList<MysandItem>();
        item.add(new MysandItem("字节跳动","不合适","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","不合适","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","不合适","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));
        item.add(new MysandItem("字节跳动","不合适","5天/周/三个月","4.0-6.0K/月","内容质量专员","杭州",R.mipmap.myexp_job_zjtd));

        return item;
    }
}