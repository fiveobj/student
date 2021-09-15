package com.example.student.my.account;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.student.R;
import com.example.student.course.stucourse.stuCourseListViewItem;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;


public class myaccountActivity extends AppCompatActivity {

    private MaterialSpinner dataselect,typeselect;
    private ImageButton back;
    private ListView listView;
    private List<MyaccountItem> list=new ArrayList<MyaccountItem>();
    private MyaccountAdapter adapter;

    //------------------------------数据------------------------------------------
    //private String[] name1=new String[]{"sfsdfs"};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);



        listView=(ListView)findViewById(R.id.myaccount_listview);
        dataselect=(MaterialSpinner)findViewById(R.id.myaccount_data);
        typeselect=(MaterialSpinner)findViewById(R.id.myaccount_type);
        list=getData_quanbu_quanbu();
        adapter=new MyaccountAdapter(this,R.layout.myaccount_item,list);
        listView.setAdapter(adapter);
        Context context=this;

        dataselect.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                switch (position){
                    case 0:
                        list=getData_quanbu_quanbu();
                        break;
                    case 1:
                        list=getData_quanbu_shouru();
                        break;
                    case 2:
                        list=getData_quanbu_zhichu();
                        break;
                    default:
                        break;

                }
                adapter=new MyaccountAdapter(context,R.layout.myaccount_item,list);
                listView.setAdapter(adapter);
            }

        });

        typeselect.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                switch (position){
                    case 0:
                        list=getData_quanbu_quanbu();
                        break;
                    case 1:
                        list=getData_shixi_quanbu();
                        break;
                    case 2:
                        list=getData_jianzhi_quanbu();
                        break;
                    case 3:
                        list=getData_xiangmu_quanbu();
                        break;
                    default:
                        break;

                }
                adapter=new MyaccountAdapter(context,R.layout.myaccount_item,list);
                listView.setAdapter(adapter);
            }
        });

    }


    public List<MyaccountItem> getData_quanbu_quanbu(){
        List<MyaccountItem> list=new ArrayList<MyaccountItem>();

        list.add(new MyaccountItem("张三","2021年8月13日   17：19","+20.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月13日   17：43","+252.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   17：23","+63.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   15：19","+2342.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   12：19","+22.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   16：19","+52.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   17：19","+622.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   15：13","+5473.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月15日   13：15","-453.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月16日   16：14","-575.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月17日   14：16","-86.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月21日   12：12","-38.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月31日   14：17","-53.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年9月1日    11：16","-73.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年9月13日   12：17","-53.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年9月14日   11：14","-83.00",R.mipmap.toux1));


        return list;
    }
    public List<MyaccountItem> getData_quanbu_zhichu(){
        List<MyaccountItem> list=new ArrayList<MyaccountItem>();

        list.add(new MyaccountItem("张三","2021年8月15日   13：15","-453.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月16日   16：14","-575.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月17日   14：16","-86.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月21日   12：12","-38.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月31日   14：17","-53.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年9月1日    11：16","-73.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年9月13日   12：17","-53.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年9月14日   11：14","-83.00",R.mipmap.toux1));

        return list;
    }
    public List<MyaccountItem> getData_quanbu_shouru(){
        List<MyaccountItem> list=new ArrayList<MyaccountItem>();
        list.add(new MyaccountItem("张三","2021年8月13日   17：19","+20.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月13日   17：43","+252.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   17：23","+63.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   15：19","+2342.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   12：19","+22.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   16：19","+52.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   17：19","+622.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   15：13","+5473.00",R.mipmap.toux1));

        return list;
    }

    public List<MyaccountItem> getData_jianzhi_quanbu(){
        List<MyaccountItem> list=new ArrayList<MyaccountItem>();
        list.add(new MyaccountItem("张三","2021年8月13日   17：19","+20.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月13日   17：43","+252.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月15日   13：15","-453.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月16日   16：14","-575.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月17日   14：16","-86.00",R.mipmap.toux1));

        return list;
    }

    public List<MyaccountItem> getData_xiangmu_quanbu(){
        List<MyaccountItem> list=new ArrayList<MyaccountItem>();
        list.add(new MyaccountItem("张三","2021年8月14日   17：23","+63.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   15：19","+2342.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   12：19","+22.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月21日   12：12","-38.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月31日   14：17","-53.00",R.mipmap.toux1));

        return list;
    }

    public List<MyaccountItem> getData_shixi_quanbu(){
        List<MyaccountItem> list=new ArrayList<MyaccountItem>();
        list.add(new MyaccountItem("张三","2021年8月14日   16：19","+52.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   17：19","+622.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年8月14日   15：13","+5473.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年9月1日    11：16","-73.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年9月13日   12：17","-53.00",R.mipmap.toux1));
        list.add(new MyaccountItem("张三","2021年9月14日   11：14","-83.00",R.mipmap.toux1));

        return list;
    }
}