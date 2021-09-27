package com.example.student.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.student.R;

public class ChatActivity extends AppCompatActivity {

    private Button sand;
    private EditText et;
    private ListView listView;
    private CharAdapter adapter;
    private ImageButton back,more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        sand=(Button)findViewById(R.id.btn_right);
        et=(EditText)findViewById(R.id.et_meg);
        listView=(ListView)findViewById(R.id.listview);
        back=(ImageButton)findViewById(R.id.char_back);
        more=(ImageButton)findViewById(R.id.char_more);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatActivity.this.finish();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        String s=et.getText().toString();

        adapter=new CharAdapter(this);

        sand.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String s=et.getText().toString();
                adapter.addDataToAdapter(s);
               // adapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(listView.getCount()-1);
                et.setText("");
                listView.setAdapter(adapter);
            }
        });

    }
}