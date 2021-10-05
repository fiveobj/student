package com.example.student.my.resume;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.student.R;
import com.example.student.contacts.ChatActivity;

import java.io.InputStream;

public class people_resumeActivity extends AppCompatActivity {
    private ImageButton down,share,change,method,back;
    private Context context;
    private ImageView image,imagebig;
    private Dialog imgdialog;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_resume);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        down=(ImageButton)findViewById(R.id.myresume_down);
        share=(ImageButton)findViewById(R.id.myresume_share);
        change=(ImageButton)findViewById(R.id.myresume_changedata);
        method=(ImageButton)findViewById(R.id.myresume_method);
        back=(ImageButton)findViewById(R.id.myresume_peo_change_back);
        image=(ImageView)findViewById(R.id.resume_method1);

        /*imgdialog=new Dialog(people_resumeActivity.this,R.style.FullActivity);

        WindowManager.LayoutParams attributes=getWindow().getAttributes();
        attributes.width=WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height=WindowManager.LayoutParams.MATCH_PARENT;
        imgdialog.getWindow().setAttributes(attributes);

        imagebig=getImageView();
        imgdialog.setContentView(image);

        imagebig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgdialog.dismiss();
            }
        });*/
        context=this;
        setlistener();
    }

   /* private ImageView getImageView(){
        ImageView imageView=new ImageView(this);

        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

        @SuppressLint("ResourceType") InputStream is=getResources().openRawResource(R.drawable.logo);
        Drawable drawable= BitmapDrawable.createFromStream(is,null);
        imageView.setImageDrawable(drawable);

        return imageView;

    }*/

    private void setlistener(){
        OnClick onClick=new OnClick();
        down.setOnClickListener(onClick);
        share.setOnClickListener(onClick);
        change.setOnClickListener(onClick);
        method.setOnClickListener(onClick);
        back.setOnClickListener(onClick);
        image.setOnClickListener(onClick);
    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.myresume_down:
                    Toast.makeText(people_resumeActivity.this,"下载成功，已保存到手机",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.myresume_share:
                    setDialog();
                    break;
                case R.id.myresume_changedata:
                    Intent intent=new Intent(people_resumeActivity.this,people_resume_changeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.myresume_method:
                    setDialogMethod();
                    break;
                case R.id.myresume_peo_change_back:
                    people_resumeActivity.this.finish();
                    break;
                /*case R.id.resume_method1:
                    imgdialog.show();
                    break;*/
                default:
                    break;

            }
        }
    }


    private void setDialog(){
        Dialog mCameraDialog=new Dialog(this,R.style.BottomDialog);
        LinearLayout root=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.resume_bottom_dialoglayout,null);
        //初始化视图
        root.findViewById(R.id.resume_share_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatActivity.class);
                startActivity(intent);
                mCameraDialog.dismiss();
            }
        });


        mCameraDialog.setContentView(root);
        Window dialogWindow=mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp=dialogWindow.getAttributes();

        lp.x=0;
        lp.y=0;
        lp.width=(int)getResources().getDisplayMetrics().widthPixels;
        root.measure(0,0);
        lp.height=root.getMeasuredHeight();
        lp.alpha=9f;
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();

    }

    private void setDialogMethod(){
        Dialog dialog=new Dialog(this,R.style.BottomDialog);
        LinearLayout root=(LinearLayout)LayoutInflater.from(this).inflate(R.layout.resume_bottom_dialog_changelayout,null);
        //初始化视图
        root.findViewById(R.id.resume_change_qxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(root);
        Window window=dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp=window.getAttributes();

        lp.x=0;
        lp.y=0;
        lp.width=(int)getResources().getDisplayMetrics().widthPixels;
        root.measure(0,0);
        lp.height=root.getMeasuredHeight();
        lp.alpha=9f;
        window.setAttributes(lp);
        dialog.show();

    }
}