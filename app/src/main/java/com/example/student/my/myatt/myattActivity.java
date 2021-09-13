package com.example.student.my.myatt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.student.R;

import java.util.ArrayList;
import java.util.List;

public class myattActivity extends AppCompatActivity {

    private ListView listView;
    private List<myattItem> list = new ArrayList<myattItem>();
    private myattAdapter adapter;
    int[] imageId = new int[]{R.mipmap.toux11, R.mipmap.toux1, R.mipmap.toux5, R.mipmap.toux10, R.mipmap.toux6, R.mipmap.toux3, R.mipmap.toux4};
    String[] title = new String[]{"刘一", "陈二", "张三", "李四", "王五", "赵六", "孙七"};
    ImageButton back;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myatt);


        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        listView=(ListView)findViewById(R.id.myatt_listview);
        //btn=
        back=(ImageButton)findViewById(R.id.myatt_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myattActivity.this.finish();
            }
        });
        list=getData();
        adapter=new myattAdapter(this,R.layout.myatt_itemlayout,list);
        listView.setAdapter(adapter);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               dialog(position);
                            }
                        });
                }
            }
        });*/
    }


    public void dialog(int position){
        attDialog attDialog=new attDialog(myattActivity.this);
        String str="确定取消对【"+title[position]+"】的关注？";
        attDialog.setCancel_tatiltv(str);
        attDialog.setCancel(new attDialog.IOnCancelListener() {
            @Override
            public void onCancel(com.example.student.my.myatt.attDialog dialog) {
                Toast.makeText(myattActivity.this,"取消成功",Toast.LENGTH_SHORT).show();
                list.remove(position);
            }
        });
        attDialog.setConfirm(new attDialog.IOnConfirmListener() {
            @Override
            public void onCancel(com.example.student.my.myatt.attDialog dialog) {
                //Toast.makeText(myattActivity.this,"取消成功",Toast.LENGTH_SHORT).show();
            }
        });
        attDialog.show();
    }

    public List<myattItem> getData(){
        List<myattItem> list = new ArrayList<myattItem>();
        list.add(new myattItem("张三",R.mipmap.toux1));
        list.add(new myattItem("张三",R.mipmap.toux1));
        list.add(new myattItem("张三",R.mipmap.toux1));
        list.add(new myattItem("张三",R.mipmap.toux1));
        return list;
    }
}