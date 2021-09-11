package com.example.student.my.collect.myclass;

import android.widget.ImageButton;

public class CollectFairItem {
    public String name,time;
    public int fairimg;
    public ImageButton button;

    public CollectFairItem(String name,String time, int image){
        this.time=time;
        this.name=name;
        this.fairimg=image;
    }

    public String getName() {
        return name;
    }

    public int getFairimg() {
        return fairimg;
    }

    public String getTime() {
        return time;
    }

}
