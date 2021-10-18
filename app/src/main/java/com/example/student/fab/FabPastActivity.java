package com.example.student.fab;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.student.R;

public class FabPastActivity extends AppCompatActivity {

    private LinearLayout tv1,tv2;
    private ImageButton back,next;
    private int x=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_past);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        back=(ImageButton)findViewById(R.id.fab_past_back);
        next=(ImageButton)findViewById(R.id.fab_past_next);
        tv1=(LinearLayout)findViewById(R.id.fab_past_tv1);
        tv2=(LinearLayout)findViewById(R.id.fab_past_tv2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabPastActivity.this.finish();
            }
        });

        tv2.setVisibility(View.INVISIBLE);
        doAlphaAndTransAnimation(tv1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(x==2){
                    tv2.setVisibility(View.VISIBLE);
                    tv1.setVisibility(View.INVISIBLE);
                    doAlphaAndTransAnimation(tv2);
                    x--;
                }

            }
        });

    }

    private void doAlphaAndTransAnimation(View view){
        ObjectAnimator alpha=ObjectAnimator.ofFloat(view,"alpha",0,1);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY",  200, 0);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(alpha).with(animatorY);
        animatorSet.setDuration(2000).start();


    }
}