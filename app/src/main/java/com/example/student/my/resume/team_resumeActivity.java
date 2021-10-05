package com.example.student.my.resume;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.student.R;

public class team_resumeActivity extends AppCompatActivity {

    private int[] arrayPicture=new int[]{R.mipmap.resume_method1,R.mipmap.background_shuimo,R.mipmap.stuc_job_ing,R.mipmap.emoji};
    private ImageSwitcher imageSwitcher;
    private int index=0;
    private float touchDownX,touchUpX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_resume);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        imageSwitcher=findViewById(R.id.resume_method_switcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(team_resumeActivity.this);
                imageView.setImageResource(arrayPicture[index]);
                return imageView;
            }
        });

        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    touchDownX=event.getX();
                    //Toast.makeText(team_resumeActivity.this,"anxia",Toast.LENGTH_SHORT).show();
                    return true;
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    touchUpX=event.getX();
                    //Toast.makeText(team_resumeActivity.this,"likai",Toast.LENGTH_SHORT).show();
                    if(touchUpX-touchDownX>100){
                        index=index==0?arrayPicture.length-1:index-1;

                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(team_resumeActivity.this, android.R.anim.fade_in));
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(team_resumeActivity.this, android.R.anim.fade_out));
                        imageSwitcher.setImageResource(arrayPicture[index]);

                    }else if(touchDownX-touchUpX>100) {
                        index = index == arrayPicture.length - 1 ? 0 : index + 1;//注意这里下标是从0开始的，所以应该是长度减1
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(team_resumeActivity.this, android.R.anim.fade_in));
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(team_resumeActivity.this, android.R.anim.fade_out));
                        imageSwitcher.setImageResource(arrayPicture[index]);
                    }
                    return true;
                }
                return false;

            }
        });
    }
}