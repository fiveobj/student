package com.example.student.my.exp;

import android.widget.ImageButton;
import android.widget.ImageView;

public class MyexpJobItem {
    public String name,type,time;
    public int img;
    public ImageButton button;

    public MyexpJobItem(String name,String type,String time,int img){
        this.img=img;
        this.name=name;
        this.time=time;
        this.type=type;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }

    public String getType() {
        return type;
    }

}
