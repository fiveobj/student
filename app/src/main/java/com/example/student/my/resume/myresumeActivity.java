package com.example.student.my.resume;

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

public class myresumeActivity extends AppCompatActivity {

    private TextView peo,team;
    private ImageButton back;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myresume);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        peo=(TextView)findViewById(R.id.myresume_peo_tv);
        team=(TextView)findViewById(R.id.myresume_team_tv);
        back=(ImageButton)findViewById(R.id.myresume_back);


        OnClick onClick=new OnClick();
        peo.setOnClickListener(onClick);
        team.setOnClickListener(onClick);
        back.setOnClickListener(onClick);

        peo.setTextColor(Color.parseColor("#1E90FF"));
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.myresume_peolayout,new resume_peoFragment());
        transaction.commit();

    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ResetImg();//改变顶部Tab为默认黑色
            transaction=manager.beginTransaction();
            switch (v.getId()){
                case R.id.myresume_peo_tv:
                    transaction.replace(R.id.myresume_peolayout,new resume_peoFragment());
                    peo.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.myresume_team_tv:
                    transaction.replace(R.id.myresume_peolayout,new resume_teamFragment());
                    team.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.myresume_back:
                    myresumeActivity.this.finish();
                    break;
                default:
                    break;
            }
            transaction.commit();
        }
    }

    private void ResetImg(){
        peo.setTextColor(Color.parseColor("#000000"));
        team.setTextColor(Color.parseColor("#000000"));
    }
}